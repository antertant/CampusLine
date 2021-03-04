package com.example.project.service;

import com.example.project.entity.User;

import java.util.List;

public interface IUserService {
    String getpassword(String username);

    int follow(String follower,String username);
    List<String> getFollower(String username);
    int countFollower(String username);

    User getuser(String username);

}
