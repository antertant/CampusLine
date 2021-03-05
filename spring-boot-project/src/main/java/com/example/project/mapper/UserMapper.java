package com.example.project.mapper;

import com.example.project.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserMapper {
    User selectByPrimaryKey(@Param(value = "username")String username);
    int updatePassword(@Param(value = "username")String username,@Param(value = "password")String password);
    List<String> selectFollow(@Param(value = "username")String username);
    List<Integer> selectCollection(@Param(value = "username")String username);
    int deleteByPrimaryKey(@Param(value = "username")String username);

    //user follower function
    List<String> selectFollower(@Param(value = "username")String username);
    int existFollower(@Param(value = "follower")String follower,@Param(value="username")String username);
    void insertFollower(@Param(value = "follower")String follower,@Param(value = "username")String username);
    void deleteFollower(@Param(value = "follower")String follower,@Param(value = "username")String username);
    List<String> getFollower(@Param(value = "username")String username);
    int countFollower(@Param(value = "username")String username);
    List<String> getFollow(@Param(value = "username")String username);
    int countFollow(@Param(value ="username")String username);
    //int isFollowed(@Param(value="username")String username,@Param(value="follower")String follower);
    //void updateF(@Param(value = "post_id")int post_id,@Param(value = "choice")int choice);
    //int insert(User record);
    //int insertSelective(User record);
}