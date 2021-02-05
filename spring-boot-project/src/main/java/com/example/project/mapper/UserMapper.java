package com.example.project.mapper;

import com.example.project.entity.User;

import java.util.List;

public interface UserMapper {
    User selectByPrimaryKey(String username);
    List<String> selectFollow(String username);
    List<String> selectFollower(String username);
    List<Integer> selectCollection(String username);
    int deleteByPrimaryKey(String username);

    
    //int insert(User record);
    //int insertSelective(User record);
}