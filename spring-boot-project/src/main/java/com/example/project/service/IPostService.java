package com.example.project.service;

public interface IPostService {
    int likepost(int post_id, String username);

    int collect(int post_id,String username);
}
