package org.sampong.onlinebanking._common.configuration;

import lombok.RequiredArgsConstructor;
import org.sampong.onlinebanking._common.exception.CustomException;
import org.sampong.onlinebanking.user.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig{
    private final UserRepository repository;

    @Bean
    UserDetailsService userDetailsService() {
        return username ->  Optional.ofNullable(repository.findByUsername(username))
                .or(() -> Optional.ofNullable(repository.findByEmail(username)))
                .map(user -> User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .disabled(!user.getEnabled())
                        .authorities(Collections.emptyList())
                        .build())
                .orElseThrow(() -> new CustomException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
