package com.ssaisriharsha.UrlShortener.Config.Security;

import com.ssaisriharsha.UrlShortener.Service.AppUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private final AppUserDetailsService userDetailsService;
    public SecurityConfig(AppUserDetailsService userDetailsService) {
        this.userDetailsService=userDetailsService;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain sfcConfig(HttpSecurity http) throws Exception {
        http
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf->csrf.disable())
                .formLogin(form->form.disable())
                .userDetailsService(userDetailsService)
                .authorizeHttpRequests(
                        auth->auth
                                .requestMatchers("/api/signup").permitAll()
                                .requestMatchers("/api/redirect/**").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(
                        session->session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        return http.build();
    }

}
