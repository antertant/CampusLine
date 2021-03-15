package com.example.project.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PostComment implements Comparable<PostComment>{
    private int comment_id;
    private int post_id;
    private String comment_user;
    private String comment_content;
    private Date comment_time;
    private int comment_likes;
    private int viewed;
    private List<CommentReply> replies;

    public void setViewed(int viewed) {
        this.viewed = viewed;
    }

    public int getViewed() {
        return viewed;
    }

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

    @Override
    public boolean equals(Object obj){
        PostComment postComment = (PostComment) obj;
        if(obj == null && this == null)
            return true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = format.format(postComment.getComment_time());
        String s1 = format.format(this.comment_time);
        if(postComment.getComment_id() == this.comment_id &&
            postComment.getPost_id() == this.post_id &&
            (postComment.getComment_content()).equals(this.comment_content) &&
            s.equals(s1)
        )
            return true;
        return false;
    }

    @Override//descending order
    public int compareTo(PostComment postComment){
        if(this.getComment_likes()>postComment.getComment_likes())
            return -1;
        else if(this.getComment_likes()<postComment.getComment_likes())
            return 1;
        else
            return 0;
    }
}
