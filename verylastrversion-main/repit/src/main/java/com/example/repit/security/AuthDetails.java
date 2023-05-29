package com.example.repit.security;

import com.example.repit.entities.Authentication;
import com.example.repit.repositories.AuthenticationRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

public class AuthDetails implements UserDetails{

    private final Authentication authentication;
    private AuthenticationRepository authenticationRepository;

    public AuthDetails(Authentication authentication) {
        this.authentication = authentication;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //роли tutor, student
        return Collections.singleton(new SimpleGrantedAuthority(authentication.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return this.authentication.getPassword();
    }

    @Override
    public String getUsername() {
        return this.authentication.get_login();
    }

    public String getRoles(){ return  this.authentication.getRole().name();}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Authentication getAuthentication(){
        return this.authentication;
    }

}
