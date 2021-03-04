package com.example.project.service.Impl;

import com.example.project.entity.User;
import com.example.project.mapper.UserMapper;
import com.example.project.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IUserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public String getpassword(String username) {
        return userMapper.selectByPrimaryKey(username).getPassword();
    }

    @Override
    public User getuser(String username) {
        User user = userMapper.selectByPrimaryKey(username);
        //user.setFollows(userMapper.selectFollow(username));
        user.setFollowers(userMapper.selectFollower(username));
        user.setCollections(userMapper.selectCollection(username));
        return user;
    }

    @Override
    public int follow(String follower, String username) {
        int existfollower = userMapper.existFollower(follower, username);
        if (existfollower == 0) {
            userMapper.insertFollower(follower, username);
            return 1;
        } else {
            userMapper.deleteFollower(follower, username);
            return 2;
        }
    }

    @Override
    public List<String> getFollower(String username){
        return userMapper.getFollower(username);
    }

    @Override
    public int countFollower(String username){
        return userMapper.countFollower(username);
    }

    @Override
    public List<String> getFollow(String username){
        return userMapper.getFollow(username);
    }

    @Override
    public int countFollow(String username){
        return userMapper.countFollow(username);
    }

    @Override
    public int isFollowed(String username, String follower){
        return userMapper.existFollower(username,follower);
    }
}
