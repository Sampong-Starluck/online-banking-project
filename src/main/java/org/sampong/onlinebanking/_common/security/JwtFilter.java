package org.sampong.onlinebanking._common.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.sampong.onlinebanking._common.utils.JwtUtil;
import org.sampong.onlinebanking.user.controller.dto.response.Context;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    // Utility to validate/parse JWT and extract claims/authorities.
    private final JwtUtil jwtUtil;
    // Local user lookup (DB/cache). Used to validate account state.
    private final UserDetailsService userDetailsService; // cached wrapper

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain)
            throws ServletException, IOException {

        // Read Authorization header
        String header = req.getHeader(HttpHeaders.AUTHORIZATION);

        // If no Bearer token present, don't authenticate here — continue.
        // This allows public endpoints or other filters to handle the request.
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(req, res);
            return;
        }

        // Strip "Bearer " prefix
        String token = header.substring(7);

        Context ctx;
        try {
            // Validate token (signature, expiry, etc.) and parse core claims.
            // jwtUtil.parseContext(...) should throw on invalid/expired tokens.
            ctx = jwtUtil.parseContext(token); // single parse+validate
        } catch (ExpiredJwtException e) {
            // Token expired -> return 401
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT Token expired");
            return;
        } catch (JwtException e) {
            // Any other JWT parsing/validation error -> 401
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Token");
            return;
        }

        // Only set authentication if token contained a username and
        // there is no Authentication already in the SecurityContext.
        if (ctx.getUsername() != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            try {
                // Load user from local store. This ensures the user still
                // exists and allows checking account flags (enabled/locked).
                UserDetails ud = userDetailsService.loadUserByUsername(ctx.getUsername());

                // Reject disabled/locked accounts
                if (!ud.isEnabled() || !ud.isAccountNonLocked()) {
                    res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not allowed");
                    return;
                }

                // Convert token claim(s) into GrantedAuthority objects.
                // IMPORTANT: jwtUtil.parseAuthorities(token) must return concrete
                // GrantedAuthority instances (e.g. SimpleGrantedAuthority) and
                // the authority strings must match your @PreAuthorize checks
                // (e.g. "ROLE_ADMIN" vs "ADMIN").
                Collection<? extends GrantedAuthority> authorities = jwtUtil.parseAuthorities(token);

                // Create Authentication. Current code uses the username as
                // principal; consider using the full UserDetails (ud) if
                // you need access to user properties later:
                //   new UsernamePasswordAuthenticationToken(ud, null, authorities)
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(ud, null, authorities);

                // Attach request details (IP, session id...) to the token
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));

                // Persist Authentication in SecurityContext for downstream use
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (UsernameNotFoundException ex) {
                // If the user referenced in the token doesn't exist locally,
                // treat token as invalid.
                res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Token");
                return;
            }
        }

        // Proceed with the filter chain
        chain.doFilter(req, res);
    }
}