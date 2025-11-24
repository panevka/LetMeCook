package com.letmecook.backend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

                private final AuthTokenFilter authTokenFilter;
                private final AuthEntryPointJwt authEntryPointJwt;
                private final OAuth2LoginSuccessHandler successHandler;

                @Bean
                public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                                http
                                                                .csrf(csrf -> csrf.disable())
                                                                .exceptionHandling(ex -> ex.authenticationEntryPoint(
                                                                                                authEntryPointJwt))
                                                                .authorizeHttpRequests(auth -> auth
                                                                                                .requestMatchers("/test")
                                                                                                .authenticated()
                                                                                                .requestMatchers("/oauth2/**", "/loginSuccess")
                                                                                                .permitAll()
                                                                                                .requestMatchers("/api/**")
                                                                                                .authenticated()
                                                                                                .anyRequest()
                                                                                                .permitAll())
                                                                .sessionManagement(sess -> sess
                                                                                                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                                                                .oauth2Login(oauth2 -> oauth2.successHandler(
                                                                                                successHandler));
                                http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);

                                return http.build();
                }
}
