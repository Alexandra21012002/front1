package com.example.repit.config;

import com.example.repit.controllers.ApplicationsRepitController;
import com.example.repit.enums.Role;
import com.example.repit.security.AuthDetails;
import com.example.repit.security.AuthProviderImpl;
import com.example.repit.security.MySimpleUrlAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import static com.example.repit.enums.Role.ROLE_STUDENT;

@Configuration
@EnableWebSecurity
//@ComponentScan("com.example.repit.security")
public class WebSecurityConfig {
    private final AuthProviderImpl authProvider;
    @Autowired
    public WebSecurityConfig(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authProvider);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/studProfile", "/StudentAds").hasRole("STUDENT")
                        .requestMatchers("/RepitProfile", "/ApplicationsRepit").hasRole("TUTOR")
                        .requestMatchers("/style.css", "/login", "/registration","/regStudent", "/regTutor").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login")
                        .successHandler(myAuthenticationSuccessHandler())
                )
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

}