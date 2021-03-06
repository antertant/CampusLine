package com.example.project.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CommentLike {
    private int post_id;
    private int comment_id;
    private String clikeuser;
    private Date like_time;
    private int viewed;

    private List<HashMap<String, String>> clike_list;
    public int getPost_id(){return post_id;}

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public Date getLike_time() {
        return like_time;
    }

    public String getClikeuser() {
        return clikeuser;
    }

    public void setClikeuser(String clikeuser) {
        this.clikeuser = clikeuser;
    }

    public void setLike_time(Date like_time) {
        this.like_time = like_time;
    }

    public int getViewed() {
        return viewed;
    }

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public List<HashMap<String, String>> getClike_list() {
        return clike_list;
    }

    public void setClike_list(List<HashMap<String, String>> clike_list) {
        this.clike_list = clike_list;
    }
}
