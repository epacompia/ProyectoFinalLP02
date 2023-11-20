package com.proyectofinal.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserCustom extends User {

    private String displayName;

    private com.proyectofinal.model.User user;

    public UserCustom(String username, String password, String displayName, Collection<? extends GrantedAuthority> authorities, com.proyectofinal.model.User user) {
        super(username, password, authorities);
        this.setDisplayName(displayName);
        this.user = user;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public com.proyectofinal.model.User getUser() {
        return user;
    }

    public void setUser(com.proyectofinal.model.User user) {
        this.user = user;
    }
}
