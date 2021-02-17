package com.example.project.service;

import java.util.List;

public interface IPostService {
    int deletePost(int post_id);

    int likepost(int post_id, String username);
    List<String> getlikes(int post_id);

    void commentpost(int post_id, String username, String content);
    void deletecomment(int comment_id);

    int collect(int post_id,String username);
}
