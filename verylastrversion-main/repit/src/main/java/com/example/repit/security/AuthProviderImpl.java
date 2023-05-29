package com.example.repit.security;

import com.example.repit.enums.Role;
import com.example.repit.methods.AuthenticationMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final AuthenticationMethods authenticationMethods;
    @Autowired
    public AuthProviderImpl(AuthenticationMethods authenticationMethods) {
        this.authenticationMethods = authenticationMethods;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String login = authentication.getName();

        UserDetails userDetails = authenticationMethods.loadUserByUsername(login);

        String password = authentication.getCredentials().toString();

        if(!password.equals(userDetails.getPassword()))
            throw new BadCredentialsException("Incorrect password!");

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
