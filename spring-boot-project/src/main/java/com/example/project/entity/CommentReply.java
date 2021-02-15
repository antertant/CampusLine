package com.example.project.entity;

import java.util.Date;

public class CommentReply {
    private int reply_id;
    private int comment_id;
    private String creply_content;
    private Date creply_time;
    private String from_user;
    private String to_user;

    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.reply_id = reply_id;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getCreply_content() {
        return creply_content;
    }

    public void setCreply_content(String creply_content) {
        this.creply_content = creply_content;
    }

    public Date getCreply_time() {
        return creply_time;
    }

    public void setCreply_time(Date creply_time) {
        this.creply_time = creply_time;
    }

    public String getFrom_user() {
        return from_user;
    }

    public void setFrom_user(String from_user) {
        this.from_user = from_user;
    }

    public String getTo_user() {
        return to_user;
    }

    public void setTo_user(String to_user) {
        this.to_user = to_user;
    }
}
