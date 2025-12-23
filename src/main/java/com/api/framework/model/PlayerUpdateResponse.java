package com.api.framework.model;

public class PlayerUpdateResponse {

    private Integer age;
    private String gender;
    private String login;
    private String role;
    private Integer id;
    private String screenName;

    public PlayerUpdateResponse() {
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getLogin() {
        return login;
    }

    public String getRole() {
        return role;
    }

    public Integer getId() {
        return id;
    }

    public String getScreenName() {
        return screenName;
    }

    @Override
    public String toString() {
        return "PlayerUpdateResponse{" +
                "age=" + age +
                ", gender='" + gender + '\'' +
                ", login='" + login + '\'' +
                ", role='" + role + '\'' +
                ", id=" + id +
                ", screenName='" + screenName + '\'' +
                '}';
    }

}
