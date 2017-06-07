package com.kevin.tabindicator.samples;

/**
 * Created by dell on 2016/6/25.
 */
public class User {
    private String name;
    private String password;
    private int sex;

    public User() {
    }

    public User(String name, String password, int sex) {
        this.name = name;
        this.password = password;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public int getSex() {
        return sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setName(String name) {
        this.name = name;
    }
}
