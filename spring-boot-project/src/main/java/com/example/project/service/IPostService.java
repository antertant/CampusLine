package com.example.project.service;

import com.example.project.entity.Post;
import com.example.project.entity.PostComment;

import java.util.List;

public interface IPostService {
    void insertPost(Post post);

    int deletePost(int post_id);

    int likepost(int post_id, String username);
    List<String> getlikes(int post_id);

    int collect(int post_id,String username);


}
