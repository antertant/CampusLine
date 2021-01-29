package com.example.project.service.Impl;

import com.example.project.entity.User;
import com.example.project.mapper.UserMapper;
import com.example.project.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class IUserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public String getpassword(String username){
        return userMapper.selectByPrimaryKey(username).getPassword();
    }

    @Override
    public User getuser(String username){
        return userMapper.selectByPrimaryKey(username);
    }
}
