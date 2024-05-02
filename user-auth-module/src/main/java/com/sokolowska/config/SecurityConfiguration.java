package com.sokolowska.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Configure CORS
        http.cors().configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    // Specify allowed origins; keep this configurable for production
                    config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
                    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization", "X-Requested-With"));
                    config.setAllowCredentials(true);
                    config.setMaxAge(3600L);
                    return config;
                })
                .and()

                // Configure CSRF protection
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringRequestMatchers("/register", "/api/routes") // Continue to disable for these routes
                .and()

                // Configure authorization requests
                .authorizeHttpRequests(authorizationRequest -> {
                    authorizationRequest
                            .requestMatchers("/login", "/register", "/api/routes").permitAll() // Allow public access
                            .requestMatchers("/routes/system", "/routes", "/welcome").authenticated(); // Require authentication
                })

                // Set up HTTP Basic authentication as default
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
    }
}

