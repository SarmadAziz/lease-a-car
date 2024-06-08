//package com.example.lease_a_car;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and().httpBasic();
//        return http.build();
//    }
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http.csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity (not recommended for production)
////                .authorizeHttpRequests(req ->
////                        req.requestMatchers()
////                        )
////                .antMatchers("/api/customers").permitAll() // Allow access to POST /api/customers without authentication
////                .anyRequest().authenticated(); // All other requests require authentication
////        return http.build();
////    }
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////
////        return http
////                .csrf(AbstractHttpConfigurer::disable)
////                .authorizeHttpRequests(
////                        req->req.requestMatchers("/login/**","/register/**", "/refresh_token/**")
////                                .permitAll()
////                                .requestMatchers("/admin_only/**").hasAuthority("ADMIN")
////                                .anyRequest()
////                                .authenticated()
////                )
////                .build();
////    }
//}