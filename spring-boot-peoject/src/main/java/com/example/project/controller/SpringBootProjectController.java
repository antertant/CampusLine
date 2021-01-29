package com.example.project.controller;

import com.example.project.entity.User;
import com.example.project.result.Result;
import com.example.project.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class SpringBootProjectController {
    @Autowired
    private IUserService iUserService;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
//        User user = new User();
//        User user1 = userMapper.selectByPrimaryKey("Mao");
        String out = "Mao's password: " + iUserService.getpassword("Mao");
        return out;
    }

    @CrossOrigin
    @RequestMapping(value = "/api/login", method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result login(@RequestBody User loginInfo) {
        String username = loginInfo.getUsername();
        String password_ = loginInfo.getPassword();
        if (iUserService.getuser(username)!=null) {
            String password = iUserService.getpassword(username);
            if (Objects.equals(password_,password)) {
                return Result.ok(null);
            }
        }
        return Result.fail();
    }
}
