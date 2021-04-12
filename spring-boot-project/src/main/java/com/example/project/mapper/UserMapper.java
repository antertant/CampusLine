package com.example.project.mapper;

import com.example.project.entity.ChatMessage;
import com.example.project.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserMapper {
    int existUser(@Param(value = "username")String username);
    int existEmail(@Param(value = "email")String email);
    int existVemail(@Param(value = "email")String email);
    void updateVcode(@Param(value = "email")String email,@Param(value = "verifycode")String verifycode);
    void insertUser(@Param(value = "username")String username,@Param(value = "password")String password,@Param(value = "email")String email);
    void deleteUser(@Param(value = "username")String username);
    User selectByPrimaryKey(@Param(value = "username")String username);
    String usernameByEmail(@Param(value = "email")String email);
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

    void createVcode(@Param(value = "email")String email,@Param(value = "verifycode")String verifycode);
    int checkVcode(@Param(value = "email")String email,@Param(value = "verifycode")String verifycode);
    void deleteVcode(@Param(value = "email")String email);
    void updatePassword_email(@Param(value = "email")String email,@Param(value = "password")String password);

    List<User> getUsers();
    void insertChat(@Param(value = "chatmessage")ChatMessage chatMessage);
    List<ChatMessage> getallnewchat(@Param(value = "username")String username);
    int countunreadchat_all(@Param(value = "username")String username);
    int countunreadchat(@Param(value = "username")String username,@Param(value = "from_user")String from_user);
    List<ChatMessage> getchat(@Param(value = "username")String username,@Param(value = "from_user")String from_user);
    void setunread(@Param(value = "username")String username, @Param(value = "from_user")String from_user);
    void setunread1(@Param(value = "username")String username, @Param(value = "from_user")String from_user);

}
