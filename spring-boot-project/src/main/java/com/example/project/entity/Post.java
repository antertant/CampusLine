package com.example.project.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {
    private int post_id;
    private int if_top;
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

    public int getIf_top() {
        return if_top;
    }

    public void setIf_top(int if_top) {
        this.if_top = if_top;
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

    public String getPost_author() { return post_author; }

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

    @Override
    public boolean equals(Object obj){
        if(obj == null && this == null)
            return true;
        Post post = (Post) obj;
        Date createtime = this.post_createtime;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String postdateStr = format.format(post.getPost_createtime());
        String dateStr = format.format(createtime);
        if(post.getPost_id() == this.post_id &&
            (post.getPost_content()).equals(this.post_content) &&
            (post.getPost_author()).equals(this.post_author) &&
            dateStr.equals(postdateStr) &&
            post.getPost_likes() == this.post_likes &&
            post.getPost_comments() == this.post_comments &&
            post.getPost_collections() == this.post_collections &&
            ((post.getModule_name()==null && this.module_name==null) ||
             (post.getModule_name()).equals(this.module_name))
        )
            return true;
        return false;
    }
}
