package com.example.project.service;

public interface IPostService {
    int likepost(int post_id, String username);

    void commentpost(int post_id, String username, String content);
    void deletecomment(int comment_id);

    int collect(int post_id,String username);
}
