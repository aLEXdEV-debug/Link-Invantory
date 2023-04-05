package com.Link.Inventory.controllers.models.enums;
import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_INSTALLER, ROLE_FOREMAN, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}