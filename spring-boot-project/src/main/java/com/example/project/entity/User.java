package com.example.project.entity;

import java.util.List;

public class User {
    private String username;
    private String password;
    private String email;
    private int permission_id;
    private String module_admin;
    private List<String> follows;
    private List<String> followers;
    private List<Integer> collections;

    public String getModule_admin() {
        return module_admin;
    }
    public void setModule_admin(String module_admin) {
        this.module_admin = module_admin;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPermission_id() {
        return permission_id;
    }

    public void setPermission_id(int permission_id) {
        this.permission_id = permission_id;
    }

    public List<String> getFollows() {
        return follows;
    }

    public void setFollows(List<String> follows) {
        this.follows = follows;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }

    public List<Integer> getCollections() {
        return collections;
    }

    public void setCollections(List<Integer> collections) {
        this.collections = collections;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}