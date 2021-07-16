package com.example.imstagram23back.security;

import com.example.imstagram23back.domain.model.User;
import com.example.imstagram23back.domain.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }


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


    private static final String ROLE_PREFIX = "ROLE_";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        UserRole userRole = user.getRole();

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(ROLE_PREFIX + userRole.toString());
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);

        return authorities;
    }
}
