package com.example.project.mapper;

import com.example.project.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User selectByPrimaryKey(@Param(value = "username")String username);
    List<String> selectFollow(@Param(value = "username")String username);
    List<String> selectFollower(@Param(value = "username")String username);
    List<Integer> selectCollection(@Param(value = "username")String username);
    int deleteByPrimaryKey(@Param(value = "username")String username);

    
    //int insert(User record);
    //int insertSelective(User record);
}