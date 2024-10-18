package com.wjh.login_application.utility;

import lombok.Generated;
import lombok.Getter;

@Getter
@Generated
public enum EndpointKey {
    REGISTER_USER("register-user");

    private final String value;
    EndpointKey(String value) {
        this.value = value;
    }
}
