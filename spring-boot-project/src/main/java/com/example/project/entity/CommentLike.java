package com.example.project.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CommentLike {
    private int comment_id;

    //user:like_date
    private List<HashMap<String, Date>> clike_list;

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public List<HashMap<String, Date>> getClike_list() {
        return clike_list;
    }

    public void setClike_list(List<HashMap<String, Date>> clike_list) {
        this.clike_list = clike_list;
    }
}