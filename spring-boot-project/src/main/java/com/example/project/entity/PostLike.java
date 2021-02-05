package com.example.project.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PostLike {
    private int post_id;

    //user:like_date
    private List<HashMap<String, Date>> like_list;

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public List<HashMap<String, Date>> getLike_list() {
        return like_list;
    }

    public void setLike_list(List<HashMap<String, Date>> like_list) {
        this.like_list = like_list;
    }
}
