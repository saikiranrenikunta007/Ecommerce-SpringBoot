package com.ecommercewebsite.ecommercewebsite.config.security;

import com.ecommercewebsite.ecommercewebsite.config.jwt.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("/items/**").permitAll()
                .requestMatchers("/authenticate", "/register").permitAll()
                .requestMatchers(HttpMethod.GET, "/**").hasAnyAuthority("Admin", "User")
                .requestMatchers("/users/**", "/user-addresses/**").hasAnyAuthority("User")
                .requestMatchers(new RegexRequestMatcher("/orders/*", "POST"),
                        new RegexRequestMatcher("/orders/*", "PUT"),
                        new RegexRequestMatcher("/orders/*", "DELETE")
                ).hasAnyAuthority("User")
                .requestMatchers("/comments/**").hasAnyAuthority("User", "Admin")
                .requestMatchers(new RegexRequestMatcher("/products/*", "PUT"),
                        new RegexRequestMatcher("/products/*", "POST"),
                        new RegexRequestMatcher("/products/*", "DELETE"))
                .hasAnyAuthority("Admin")
                .requestMatchers("/payments/**").hasAnyAuthority("User")
                .requestMatchers("/categories/**").hasAnyAuthority("Admin")
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
