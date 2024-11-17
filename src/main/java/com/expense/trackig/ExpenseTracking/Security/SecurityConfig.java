//package com.expense.trackig.ExpenseTracking.Security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Autowired
//    UserDetailsService userDetailSevice;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.authorizeHttpRequests(req -> req
//                        .requestMatchers("/public/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(formLogin -> formLogin
//                        .loginPage("/user/login")
//                        .defaultSuccessUrl("/dashboard", true)
//                        .permitAll()
//                )
//                .build();
////                .logout(logout -> logout
////                        .logoutUrl("/logout")
////                        .logoutSuccessUrl("/login?logout")
////                        .permitAll()
////                );
//    }
//
//    @Autowired
//    public void configureAuth(AuthenticationManagerBuilder authBuilder) throws Exception {
//        authBuilder.userDetailsService(userDetailSevice).passwordEncoder(new BCryptPasswordEncoder());
//    }
//}
