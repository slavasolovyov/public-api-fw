package com.api.framework.model;

public class PlayerGetByPlayerIdResponse {

    private Integer age;
    private String gender;
    private Integer id;
    private String role;
    private String login;
    private String password;
    private String screenName;

    public PlayerGetByPlayerIdResponse() {
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Integer getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getScreenName() {
        return screenName;
    }

    @Override
    public String toString() {
        return "PlayerGetByPlayerIdResponse{" +
                "age=" + age +
                ", gender='" + gender + '\'' +
                ", id=" + id +
                ", role='" + role + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", screenName='" + screenName + '\'' +
                '}';
    }
}
