package com.proyectofinal.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserCustom extends User {

    private String displayName;

    public UserCustom(String username, String password, String displayName, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.setDisplayName(displayName);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
