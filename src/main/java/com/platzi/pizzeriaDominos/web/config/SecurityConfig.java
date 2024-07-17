package com.platzi.pizzeriaDominos.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity (securedEnabled = true)
public class SecurityConfig {

    private static final String ADMIN = "ADMIN";
    private static final String CUSTOMER = "CUSTOMER";
    private static final String RANDOM_ORDER = "random_order";

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http
                .csrf( csrf -> csrf.disable())
                .cors(withDefaults())
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests -> {
                    authorizeRequests.requestMatchers("/api/auth/**").permitAll();
                    authorizeRequests.requestMatchers("/api/customer/**").hasAnyRole(ADMIN,CUSTOMER);
                    authorizeRequests.requestMatchers(HttpMethod.GET,"/api/pizzas/**").hasAnyRole(ADMIN,CUSTOMER);
                    authorizeRequests.requestMatchers(HttpMethod.POST,"/api/pizzas/**").hasRole(ADMIN);
                    authorizeRequests.requestMatchers(HttpMethod.PUT).hasRole(ADMIN);
                    authorizeRequests.requestMatchers("/api/orders/random").hasAuthority(RANDOM_ORDER);
                    authorizeRequests.requestMatchers("/api/orders/**").hasRole(ADMIN);
                    authorizeRequests.anyRequest().authenticated();
                })
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

}
