package com.example.project.service;

import com.example.project.entity.User;

public interface IUserService {
    String getpassword(String username);

    User getuser(String username);
}
