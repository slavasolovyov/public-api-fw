package com.api.framework.utils;

import com.api.framework.enums.Gender;
import com.api.framework.enums.Roles;

import java.util.HashMap;
import java.util.Map;

public class PlayerTestDataBuilder {
    
    private String screenName;
    private String login;
    private String password;
    private Integer age;
    private String gender;
    private String role;

    private PlayerTestDataBuilder() {
        this.screenName = TestDataFactory.randomScreenName();
        this.login = TestDataFactory.randomLogin();
        this.password = TestDataFactory.randomAlphabeticPassword(7);
        this.age = TestDataFactory.getMinValidAge();
        this.gender = Gender.MALE.getValue();
        this.role = Roles.USER.getValue();
    }

    public static PlayerTestDataBuilder defaultPlayer() {
        return new PlayerTestDataBuilder();
    }

    public PlayerTestDataBuilder withScreenName(String screenName) {
        this.screenName = screenName;
        return this;
    }

    public PlayerTestDataBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public PlayerTestDataBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public PlayerTestDataBuilder withAge(Integer age) {
        this.age = age;
        return this;
    }

    public PlayerTestDataBuilder withGender(String gender) {
        this.gender = gender;
        return this;
    }

    public PlayerTestDataBuilder withGender(Gender gender) {
        this.gender = gender.getValue();
        return this;
    }

    public PlayerTestDataBuilder withRole(String role) {
        this.role = role;
        return this;
    }

    public PlayerTestDataBuilder withRole(Roles role) {
        this.role = role.getValue();
        return this;
    }

    public Map<String, Object> build() {
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("screenName", screenName);
        queryParams.put("login", login);
        queryParams.put("password", password);
        queryParams.put("age", age);
        queryParams.put("gender", gender);
        queryParams.put("role", role);
        return queryParams;
    }
}

