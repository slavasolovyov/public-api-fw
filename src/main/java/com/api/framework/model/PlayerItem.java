package com.api.framework.model;

import java.util.Objects;

public class PlayerItem {

    private Integer age;
    private String gender;
    private Integer id;
    private String role;
    private String screenName;

    public PlayerItem() {
    }

    @Override
    public String toString() {
        return "PlayerItem{" +
                "age=" + age +
                ", gender='" + gender + '\'' +
                ", id=" + id +
                ", role='" + role + '\'' +
                ", screenName='" + screenName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PlayerItem)) return false;
        PlayerItem that = (PlayerItem) o;
        return Objects.equals(getAge(), that.getAge()) && Objects.equals(getGender(), that.getGender())
                && Objects.equals(getId(), that.getId()) && Objects.equals(getRole(), that.getRole())
                && Objects.equals(getScreenName(), that.getScreenName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAge(), getGender(), getId(), getRole(), getScreenName());
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
}
