package com.api.framework.enums;

public enum Roles {

    SUPERVISOR("supervisor"),
    ADMIN("admin"),
    USER("user");

    private final String value;

    Roles(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
