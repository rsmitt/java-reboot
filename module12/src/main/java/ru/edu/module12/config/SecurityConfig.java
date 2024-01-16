package ru.edu.module12.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.edu.module12.entity.Auth;
import ru.edu.module12.repository.AuthRepository;

import java.util.stream.Collectors;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private AuthRepository authRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Auth auth = authRepository.findByUsername(username);
            if (auth == null) {
                throw new UsernameNotFoundException("Пользователь " + username + " не найден.");
            }
            return new org.springframework.security.core.userdetails.User(
                    auth.getUsername(),
                    auth.getPassword(),
                    auth.getRoles().stream()
                            .map(role -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList()));
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.GET).hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.POST).hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
