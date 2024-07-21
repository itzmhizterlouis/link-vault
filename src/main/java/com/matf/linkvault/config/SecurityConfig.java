package com.matf.linkvault.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

         return http.authorizeHttpRequests(auth ->
             auth
                     .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                     .anyRequest().authenticated())
                 .oauth2Login(Customizer.withDefaults())
                 .logout(l -> l
                         .logoutSuccessUrl("/").permitAll()
                 )
                 .build();
    }
}
