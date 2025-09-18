package org.sampong.onlinebanking.user.service.implement;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.sampong.onlinebanking._common.exception.CustomException;
import org.sampong.onlinebanking._common.utils.JwtUtil;
import org.sampong.onlinebanking.user.controller.dto.request.CreateUserRequest;
import org.sampong.onlinebanking.user.controller.dto.request.ForgetPasswordRequest;
import org.sampong.onlinebanking.user.controller.dto.request.JwtRequest;
import org.sampong.onlinebanking.user.controller.dto.request.UserPageRequest;
import org.sampong.onlinebanking.user.controller.dto.response.JwtResponse;
import org.sampong.onlinebanking.user.model.UserDetail;
import org.sampong.onlinebanking.user.model.Users;
import org.sampong.onlinebanking.user.repository.UserDetailRepository;
import org.sampong.onlinebanking.user.repository.UserRepository;
import org.sampong.onlinebanking.user.service.RoleService;
import org.sampong.onlinebanking.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository repository;
    private final UserDetailRepository userDetailRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public JwtResponse login(JwtRequest jwtRequest) throws JsonProcessingException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (AuthenticationException e) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, e.getLocalizedMessage());
        }
        var user = repository.findByUsername(jwtRequest.getUsername());
        return jwtUtil.generateToken(user);
    }

    @Override
    public Users register(CreateUserRequest createUserRequest) {
        return createUser(createUserRequest);
    }

    @Override
    public JwtResponse forgetPassword(ForgetPasswordRequest createUserRequest) throws JsonProcessingException {
        var user = Optional.ofNullable(repository.findByEmail(createUserRequest.getEmail())).orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "User not found"));
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        repository.save(user);
        return jwtUtil.generateToken(user);
    }

    @Override
    public Page<Users> findAllUsers(UserPageRequest pageRequest) {
        var page = pageRequest.getPage();
        var size = pageRequest.getSize();
        return repository.findAll((root, quey, cb) -> {
            var predicate = new ArrayList<Predicate>();
            if (pageRequest.getQuery() != null) {
                var username = cb.equal(root.get("username"), pageRequest.getQuery());
                var email = cb.equal(root.get("email"), pageRequest.getQuery());
                var firstName = root.get("userDetail").get("firstName").as(String.class);
                var lastName = root.get("userDetail").get("lastname").as(String.class);
                var fullName = cb.concat(cb.concat(firstName, " "), lastName);
                predicate.add(cb.or(username, email, cb.like(fullName, pageRequest.getQuery())));
            }
            // Always filter by status = true
            predicate.add(cb.equal(root.get("status"), true));

            return cb.and(predicate.toArray(new Predicate[0]));
        }, PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")));
    }

    private Users createUser(CreateUserRequest user) {
        var users = new Users();
        var roles = roleService.findAllByIds(user.getRoles());
        if (roles.isEmpty()) {
            throw new CustomException(HttpStatus.NOT_FOUND, "Roles not valid");
        }
        users.setUsername(user.getFirstName()+"_"+user.getLastName());
        users.setEmail(user.getEmail());
        if (user.getAdmin() == null) {
            users.setAdmin(false);
        } else {
            users.setAdmin(user.getAdmin());
        }
        users.setEnabled(true);
        var existUsername = repository.existsByUsernameAndStatusTrue(users.getUsername());
        var existEmail = repository.existsByEmailAndStatusTrue(users.getEmail());
        if (existUsername || existEmail) {
            throw new CustomException(HttpStatus.NOT_ACCEPTABLE, "Username or email already exists!");
        }
        var result = repository.save(users);
        var detail = new UserDetail();
        detail.setUser(result);
        detail.setRoles(roles);
        detail.setFirstName(user.getFirstName());
        detail.setLastName(user.getLastName());
        detail.setPhoneNumber(user.getPhoneNumber());
        var saveDetail = userDetailRepository.save(detail);

        result.setUserDetail(saveDetail);
        repository.save(result);
        return result;
    }
}
