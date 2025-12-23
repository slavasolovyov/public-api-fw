package com.api.framework.model;

import java.util.Objects;

public class PlayerCreateResponse {

    private Integer age;
    private String gender;
    private Integer id;
    private String role;
    private String screenName;
    private String login;
    private String password;

    public PlayerCreateResponse() {
    }

    @Override
    public String toString() {
        return "PlayerCreateResponse{" +
                "age=" + age +
                ", gender='" + gender + '\'' +
                ", id=" + id +
                ", role='" + role + '\'' +
                ", screenName='" + screenName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PlayerCreateResponse)) return false;
        PlayerCreateResponse that = (PlayerCreateResponse) o;
        return Objects.equals(getAge(), that.getAge()) && Objects.equals(getGender(), that.getGender())
                && Objects.equals(getId(), that.getId()) && Objects.equals(getRole(), that.getRole())
                && Objects.equals(getScreenName(), that.getScreenName())
                && Objects.equals(getLogin(), that.getLogin())
                && Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAge(), getGender(), getId(), getRole(), getScreenName(), getLogin(), getPassword());
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

    public String getScreenName() {
        return screenName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
