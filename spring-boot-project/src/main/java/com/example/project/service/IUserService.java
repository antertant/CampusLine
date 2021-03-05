package com.example.project.service;

import com.example.project.entity.User;

import java.util.List;

public interface IUserService {
    String getpassword(String username);
    void updatePassword(String uername,String password);

    int follow(String follower,String username);
    List<String> getFollower(String username);
    int countFollower(String username);
    List<String> getFollow(String username);
    int countFollow(String username);
    int isFollowed(String username, String follower);

    User getuser(String username);

}
