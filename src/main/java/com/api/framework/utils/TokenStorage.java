package com.api.framework.utils;

import com.api.framework.enums.Roles;

import java.util.concurrent.ConcurrentHashMap;

public class TokenStorage {

    private static final ConcurrentHashMap <Roles, String> map = new ConcurrentHashMap<>();

    public static String getToken(Roles role) {
        return map.get(role);
    }

    public static void setToken(Roles role, String token) {
        map.put(role, token);
    }
}
