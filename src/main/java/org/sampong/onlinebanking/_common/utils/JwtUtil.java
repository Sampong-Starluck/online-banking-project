package org.sampong.onlinebanking._common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.sampong.onlinebanking._common.enumerate.RoleStatus;
import org.sampong.onlinebanking.user.controller.dto.response.Context;
import org.sampong.onlinebanking.user.controller.dto.response.JwtResponse;
import org.sampong.onlinebanking.user.model.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    @Value("${jwt.secret:mySecretKey}")
    private String secret;
    @Value("${jwt.expiration:86400}") // 24 hours in seconds
    private Long expiration;

    //    private final JwtParser parser = Jwts.parser().verifyWith(getSigningKey()).build();
    private final ObjectMapper objectMapper;
    private final String expectedAlg = Jwts.SIG.HS256.getId();

    public Context parseContext(String token) throws JsonProcessingException {
        return objectMapper.readValue(getClaimFromToken(token, Claims::getSubject), Context.class);
    }

    public List<SimpleGrantedAuthority> parseAuthorities(String token) {
        Claims claims = getAllClaimsFromToken(token);
        Object raw = claims.get("authorities");
        if (!(raw instanceof List<?> list))
            return List.of();

        if (list.isEmpty()) return List.of();

        Object first = list.getFirst();
        if (first instanceof String) {
            return list.stream()
                    .map(Object::toString)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        } else if (first instanceof Map) {
            return list.stream()
                    .map(o -> ((Map<?, ?>) o).get("authority"))
                    .filter(Objects::nonNull)
                    .map(Object::toString)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(getAllClaimsFromToken(token));
    }

    private Claims getAllClaimsFromToken(String token) throws ExpiredJwtException {
        var jws = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);
        String alg = jws.getHeader().getAlgorithm();
        if (!expectedAlg.equals(alg)) {
            throw new JwtException("Unexpected JWT alg: " + alg);
        }
        return jws.getPayload();

    }

    /**
     * Generate token for user (admin)
     */
    public JwtResponse generateToken(Users req) throws JsonProcessingException {
        var roles = req.getUserDetail().getRoles();
        var roleStatuses = new ArrayList<RoleStatus>();
        for (var role : roles) {
            roleStatuses.add(role.getRoleStatus());
        }
        var context = new Context(req.getId(), req.getUsername(), roleStatuses);
        var subject = new ObjectMapper().writeValueAsString(context);

        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", getAuthorities(roleStatuses));

        return doGenerateToken(claims, subject);
    }

    /**
     * Core token generation logic
     */
    private JwtResponse doGenerateToken(Map<String, Object> claims, String subject) {
        Date created = new Date();
        Date expired = new Date(created.getTime() + (expiration * 60 * 1000));

        String jwtToken = Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expired)
                .signWith(getSigningKey())
                .compact();

        var response = new JwtResponse();
        response.setToken(jwtToken);
        response.setExpiredIn(expired.getTime());
        return response;
    }

//    public boolean validateToken(String token) {
//        return !isTokenExpired(token);
//    }

//    private boolean isTokenExpired(String token) {
//        return getExpirationDate(token).before(new Date());
//    }

//    private Date getExpirationDate(String token) {
//        return getClaimFromToken(token, Claims::getExpiration);
//    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private List<SimpleGrantedAuthority> getAuthorities(List<RoleStatus> roles) {
        if (roles == null || roles.isEmpty()) {
            return Collections.emptyList();
        }

        return roles.stream()
                .filter(Objects::nonNull)
                .map(RoleStatus::name)
                .map(String::trim)
                .map(String::toUpperCase)
//                .map(name -> name.startsWith("ROLE_") ? name : "ROLE_" + name)
                .distinct()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
