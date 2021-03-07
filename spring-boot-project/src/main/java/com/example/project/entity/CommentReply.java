package com.example.project.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommentReply {
    private int post_id;
    private int reply_id;
    private int comment_id;
    private String creply_content;
    private Date creply_time;
    private String from_user;
    private String to_user;
    private int viewed;

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public int getViewed() {
        return viewed;
    }

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }

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

    @Override
    public boolean equals(Object obj){
        CommentReply commentReply = (CommentReply) obj;
        if(obj == null && this == null)
            return true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = format.format(commentReply.getCreply_time());
        String s1 = format.format(this.creply_time);
        if(commentReply.getComment_id() == this.comment_id &&
            commentReply.getReply_id() == this.reply_id &&
            (commentReply.getCreply_content()).equals(this.creply_content) &&
            s.equals(s1) &&
            (commentReply.getFrom_user()).equals(this.from_user)&&
            (commentReply.getTo_user()).equals(this.to_user)
        )
            return true;
        return false;
    }

}
