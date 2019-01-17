package com.authentication.authenticationserver.infrastracture.jwt;

import com.authentication.authenticationserver.authentication.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfiguration {

    @Bean
    public JwtAuthTokenFilter jwtAuthTokenFilter() {

        JwtProvider jwtProvider = new JwtProvider();
        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();

        return new JwtAuthTokenFilter(jwtProvider, userDetailsService);
    }
}
