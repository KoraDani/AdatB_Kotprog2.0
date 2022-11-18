package com.okosotthon.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;

public class CustomUserDetails implements UserDetails {
    private Users users;

    public CustomUserDetails(Users users) {
        System.out.println("CustomUserDetail letrehozva "+ users.getFelh());
        this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> role = new ArrayList<>();
        role.add(new SimpleGrantedAuthority("USER"));
        return role;
    }

    @Override
    public String getPassword() {
        return users.getJelszo();
    }

    @Override
    public String getUsername() {
        return users.getFelh();
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
}
