package com.wjh.login_application.utility;

import lombok.Generated;
import lombok.Getter;

@Getter
@Generated
public enum PageKey {
    ROOT("root"),
    REGISTER("register"),
    LOGIN("login"),
    HOME("home"),
    MANAGER_EXCLUSIVE("manager-exclusive");

    private final String value;
    PageKey(String value) {
        this.value = value;
    }
}
