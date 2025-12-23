package com.api.framework.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerUpdateRequest {

    @JsonProperty("age")
    private Integer age;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("login")
    private String login;
    @JsonProperty("role")
    private String role;
    @JsonProperty("password")
    private String password;
    @JsonProperty("screenName")
    private String screenName;

    public PlayerUpdateRequest(Integer age, String gender, String login, String role, String password, String screenName) {
        this.age = age;
        this.gender = gender;
        this.login = login;
        this.role = role;
        this.password = password;
        this.screenName = screenName;
    }

    public PlayerUpdateRequest() {
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}
