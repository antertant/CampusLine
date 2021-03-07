package com.example.project.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PostLike {
    private int post_id;
    private String like_user;
    private Date like_time;
    private int viewed;
    private List<HashMap<String, String>> like_list;

    public int getPost_id() {
        return post_id;
    }

    public int getViewed() {
        return viewed;
    }

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }

    public void setLike_time(Date like_time) {
        this.like_time = like_time;
    }

    public Date getLike_time() {
        return like_time;
    }

    public String getLike_user() {
        return like_user;
    }

    public void setLike_user(String like_user) {
        this.like_user = like_user;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public List<HashMap<String, String>> getLike_list() {
        return like_list;
    }

    public void setLike_list(List<HashMap<String, String>> like_list) {
        this.like_list = like_list;
    }
}
