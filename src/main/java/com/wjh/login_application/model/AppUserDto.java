package com.wjh.login_application.model;

import lombok.Generated;

@Generated
public record AppUserDto(String name, String username, String role) {
    public AppUserDto(String name, String username, String role) {
        this.name = name;
        this.username = username;
        this.role = role.substring(0, 1).toUpperCase() + role.substring(1).toLowerCase();
    }
}
