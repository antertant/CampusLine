package com.example.project.entity;

import java.util.Date;

public class Post {
    private int post_id;
    private String module_name;
    private String post_content;
    private String post_author;
    private Date post_createtime;
    private int post_likes;
    private int post_comments;
    private int post_collections;

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public String getPost_author() {
        return post_author;
    }

    public void setPost_author(String post_author) {
        this.post_author = post_author;
    }

    public Date getPost_createtime() {
        return post_createtime;
    }

    public void setPost_createtime(Date post_createtime) {
        this.post_createtime = post_createtime;
    }

    public int getPost_likes() {
        return post_likes;
    }

    public void setPost_likes(int post_likes) {
        this.post_likes = post_likes;
    }

    public int getPost_comments() {
        return post_comments;
    }

    public void setPost_comments(int post_comments) {
        this.post_comments = post_comments;
    }

    public int getPost_collections() {
        return post_collections;
    }

    public void setPost_collections(int post_collections) {
        this.post_collections = post_collections;
    }
}
