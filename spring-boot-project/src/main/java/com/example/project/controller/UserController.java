package com.example.project.controller;

import com.example.project.entity.ChatMessage;
import com.example.project.entity.User;
import com.example.project.mapper.UserMapper;
import com.example.project.result.Result;
import com.example.project.service.IUserService;
import com.power.common.util.RandomUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class UserController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    JavaMailSenderImpl mailSender;
    @Resource
    private UserMapper userMapper;

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
        Optional<User>userOptional = Optional.ofNullable(user);

        if (userOptional.isPresent()){
            String passwordLoginIn = loginInfo.getPassword();
            if (userOptional.stream()
                    .anyMatch((userValue)->userValue
                            .getPassword()
                            .equals(passwordLoginIn)))
                return Result.ok(user);
        }
        return Result.fail("fail");
    }

    @CrossOrigin
    @RequestMapping(value = "/api/login_mail", method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result login_mail(@RequestBody User loginInfo) {
        User user = iUserService.getuser_email(loginInfo.getEmail());
        Optional<User> userOptional = Optional.ofNullable(user);

        if (userOptional.isPresent()) {
            String passwordLoginIn = loginInfo.getPassword();
            if (userOptional.stream()
                    .anyMatch(userValue -> userValue
                            .getPassword()
                            .equals(passwordLoginIn)))
                return Result.ok(user);
        }
        return Result.fail("fail");
    }

    @CrossOrigin
    @ApiOperation(value = "send verify code to email")
    @RequestMapping(value = "/api/sendmail",method = RequestMethod.POST)
    @ResponseBody
    public Result sendmail(@RequestParam("email")String email){
        SimpleMailMessage message=new SimpleMailMessage();
        String verifyCode= RandomUtil.randomNumbers(4);
        message.setSubject("VerifyCode");
        message.setText(verifyCode);
        message.setTo(email);
        message.setFrom("Hazixn_foal7713@qq.com");
        mailSender.send(message);
        if(userMapper.existVerifyEmail(email)!=0){
            userMapper.updateVerifyCode(email,verifyCode);
        }else
            userMapper.createVerifyCode(email,verifyCode);
        return Result.ok("verify code send successful");
    }

    @CrossOrigin
    @ApiOperation(value = "register")
    @RequestMapping(value = "/api/register",method = RequestMethod.POST)
    @ResponseBody
    public Result register(@RequestParam("username")String username,
                           @RequestParam("password")String password,
                           @RequestParam("email")String email,
                           @RequestParam("verifycode")String verifycode) {
        if(userMapper.existUser(username)==0){
            if(userMapper.existEmail(email)==0){
                if(userMapper.checkVerifyCode(email,verifycode)==1){
                    //registration code>>>
                    userMapper.insertUser(username,password,email);

                }else return Result.fail("wrong verify code!");
            }else return Result.fail("email exist!");
        }else return Result.fail("username exist!");
        return Result.ok("register finish");
    }

    @CrossOrigin
    @ApiOperation(value = "update password")
    @RequestMapping(value = "/api/updatepassword", method = RequestMethod.POST)
    @ResponseBody
    public Result updatePassword(@RequestParam("username")String username,
                                 @RequestParam("password")String password,
                                 @RequestParam("oldpassword")String oldpassword){
        String oldPassword=iUserService.getpassword(username);
        if(Objects.equals(oldPassword,oldpassword)){ //verify old password and update new
            iUserService.updatePassword(username,password);
            return Result.ok("update password successfully");
        }else
            return Result.fail("old password is incorrect");
        //return Result.ok("update password successfully");
    }

    @CrossOrigin
    @ApiOperation(value = "update password by email verify code")
    @RequestMapping(value = "/api/updatepassword_email", method = RequestMethod.POST)
    @ResponseBody
    public Result updatePassword_email(@RequestParam("email")String email,
                                       @RequestParam("verifycode")String verifycode,
                                       @RequestParam("password")String password){
        if(userMapper.checkVerifyCode(email,verifycode)==1){
            iUserService.updatePassword_email(email,password);
            //change the verify code in case user use the same verifycode to change password continuously
            String vCode= RandomUtil.randomNumbers(4);
            userMapper.updateVerifyCode(email,vCode);

            return Result.ok("update password successfully");
        }else
            return Result.fail("email or verify code is incorrect");
    }

    @CrossOrigin
    @ApiOperation(value = "follow user or cancel follow")
    @RequestMapping(value = "/api/follow", method = RequestMethod.POST)
    @ResponseBody
    public Result follow(@RequestParam("follower")String follower,
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
    @ApiOperation(value = "send message to another user")
    @RequestMapping(value = "/api/chatsend", method = RequestMethod.POST)
    @ResponseBody
    public Result chatSend(@RequestBody ChatMessage chatMessage){
        userMapper.insertChat(chatMessage);
        return Result.ok("send chat successfully");
    }

    @CrossOrigin
    @ApiOperation(value = "get all newest chat list")
    @RequestMapping(value = "/api/getallnewestchat", method = RequestMethod.GET)
    @ResponseBody
    public Result getAllNewestChat(@RequestParam("username")String username){
        List<ChatMessage> chatMessages = userMapper.getAllNewChat(username);
        return Result.ok(chatMessages);
    }

    @CrossOrigin
    @ApiOperation(value = "count all unread chat of a user")
    @RequestMapping(value = "/api/countunreadchat_all", method = RequestMethod.GET)
    @ResponseBody
    public Result countUnreadChat_All(@RequestParam("username")String username){
        int count = userMapper.countUnreadChat_All(username);
        return Result.ok(count);
    }

    @CrossOrigin
    @ApiOperation(value = "count unread chat of one user to one user")
    @RequestMapping(value = "/api/countunreadchat", method = RequestMethod.GET)
    @ResponseBody
    public Result countUnreadChat(@RequestParam("username")String username,
                                  @RequestParam("from_user")String from_user){
        int count = userMapper.countUnreadChat(username,from_user);
        return Result.ok(count);
    }

    @CrossOrigin
    @ApiOperation(value = "cycle: get chat content && set unread==0")
    @RequestMapping(value = "/api/getchat", method = RequestMethod.GET)
    @ResponseBody
    public Result getChat(@RequestParam("username")String username,
                          @RequestParam("from_user")String from_user){
        List<ChatMessage> chat = userMapper.getChat(username,from_user);
        userMapper.setUnread(username, from_user);
        return Result.ok(chat);
    }

    @CrossOrigin
    @ApiOperation("get the follower_list of a user")
    @RequestMapping(value = "/api/getfollower", method = RequestMethod.GET)
    @ResponseBody
    public Result getFollower(@RequestParam("username")String username){
        List<String> followers = iUserService.getFollower(username);
        return Result.ok(followers);
    }

    @CrossOrigin
    @ApiOperation("count the followers")
    @RequestMapping(value="/api/countfollower", method = RequestMethod.GET)
    @ResponseBody
    public Result countFollower(@RequestParam("username")String username){
        int countFollower = iUserService.countFollower(username);
        return Result.ok(countFollower);
    }

    @CrossOrigin
    @ApiOperation("get the follow_list of a user")
    @RequestMapping(value = "/api/getfollow", method = RequestMethod.GET)
    @ResponseBody
    public Result getFollow(@RequestParam("username")String username){
        List<String> follow = iUserService.getFollow(username);
        return Result.ok(follow);
    }

    @CrossOrigin
    @ApiOperation("count the follow")
    @RequestMapping(value="/api/countfollow", method = RequestMethod.GET)
    @ResponseBody
    public Result countFollow(@RequestParam("username")String username){
        int countFollow = iUserService.countFollow(username);
        return Result.ok(countFollow);
    }

    @CrossOrigin
    @ApiOperation("whether followed")
    @RequestMapping(value="/api/isfollowed", method = RequestMethod.GET)
    @ResponseBody
    public Result isFollowed(@RequestParam("username")String username,
                             @RequestParam("follower")String follower){
        int isFollowed = iUserService.isFollowed(username,follower);
        return Result.ok(isFollowed);
    }

    @CrossOrigin
    @ApiOperation("get user list for managemant")
    @RequestMapping(value="/api/getusers", method = RequestMethod.GET)
    @ResponseBody
    public Result getUsers(){
        List<User> list = userMapper.getUsers();
        return Result.ok(list);
    }

    @CrossOrigin
    @ApiOperation("delete user account")
    @RequestMapping(value="/api/deleteuser", method = RequestMethod.POST)
    @ResponseBody
    public Result deleteUser(@RequestParam("username")String username){
        userMapper.deleteByPrimaryKey(username);
        return Result.ok("delete successfully");
    }

}
