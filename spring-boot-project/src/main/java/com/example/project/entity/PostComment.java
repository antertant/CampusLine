package com.example.project.entity;

import java.util.Date;
import java.util.List;

public class PostComment {
    private int comment_id;
    private int post_id;
    private String comment_user;
    private String comment_content;
    private Date comment_time;
    private int comment_likes;
    private List<CommentReply> replies;

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getComment_user() {
        return comment_user;
    }

    public void setComment_user(String comment_user) {
        this.comment_user = comment_user;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public Date getComment_time() {
        return comment_time;
    }

    public void setComment_time(Date comment_time) {
        this.comment_time = comment_time;
    }

    public int getComment_likes() {
        return comment_likes;
    }

    public void setComment_likes(int comment_likes) {
        this.comment_likes = comment_likes;
    }

    public List<CommentReply> getReplies() {
        return replies;
    }

    public void setReplies(List<CommentReply> replies) {
        this.replies = replies;
    }
}
