package com.example.project.controller;

import com.example.project.entity.User;
import com.example.project.result.Result;
import com.example.project.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Controller
public class UserController {
    @Autowired
    private IUserService iUserService;

//    @RequestMapping("/hello")
//    @ResponseBody
//    public String hello() {
//        User user = new User();
//        User user1 = userMapper.selectByPrimaryKey("Mao");
//        String out = "Mao's password: " + iUserService.getpassword("Mao");
//        return out;
//    }
    @CrossOrigin
    @RequestMapping(value = "/api/login", method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result login(@RequestBody User loginInfo) {
        User user = iUserService.getuser(loginInfo.getUsername());

        String username = loginInfo.getUsername();
        String password_ = loginInfo.getPassword();
        if (iUserService.getuser(username)!=null) {
            String password = iUserService.getpassword(username);
            if (Objects.equals(password_,password)) {
                return Result.ok(user);
            }
        }
        return Result.fail("fail");
    }

    @CrossOrigin
    @ApiOperation(value = "follow user or cancel follow")
    @RequestMapping(value = "/api/follow", method = RequestMethod.POST)
    @ResponseBody
    public Result collect(@RequestParam("follower")String follower,
                          @RequestParam("username")String username){
        int flag = iUserService.follow(follower,username);
        String msg;
        if(flag == 1)
            msg = "follow successfully";
        else
            msg = "cancel follow successfully";
        return Result.ok(msg);
    }

    @CrossOrigin
    @ApiOperation("get the follower_list of a user")
    @RequestMapping(value = "/api/getfollower", method = RequestMethod.GET)
    @ResponseBody
    public Result getfollower(@RequestParam("username")String username){
        List<String> followers = iUserService.getFollower(username);
        return Result.ok(followers);
    }


}
