package com.wjh.login_application.utility;

import lombok.Generated;
import lombok.Getter;

import java.util.List;
import java.util.stream.Stream;

@Getter
@Generated
public enum RoleKey {
    ADMIN("ADMIN"),
    MANAGER("MANAGER"),
    USER("USER");

    private final String value;

    RoleKey(String value) {
        this.value = value;
    }

    public static String[] defineRoles(List<RoleKey> roleKeys) {
        return roleKeys.stream()
                .flatMap(roleKey -> Stream.of(roleKey.value.toUpperCase(), roleKey.value.toLowerCase()))
                .toArray(String[]::new);
    }
}
