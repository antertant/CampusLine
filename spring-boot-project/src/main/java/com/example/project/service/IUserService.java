package com.example.project.service;

import com.example.project.entity.PostLike;
import com.example.project.entity.User;

import java.util.List;

public interface IUserService {
    String getpassword(String username);
    String getpassword_email(String email);
    void updatePassword(String uername,String password);
    void updatePassword_email(String email,String password);

    int follow(String follower,String username);
    List<String> getFollower(String username);
    int countFollower(String username);
    List<String> getFollow(String username);
    int countFollow(String username);
    int isFollowed(String username, String follower);

    User getuser(String username);
    User getuser_email(String email);


}
