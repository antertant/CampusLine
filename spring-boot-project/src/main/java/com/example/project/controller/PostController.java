package com.example.project.controller;

import com.example.project.result.Result;
import com.example.project.service.IPostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    @Autowired
    private IPostService iPostService;

    @CrossOrigin
    @ApiOperation("like or cancel like, add author's points of that module")
    @RequestMapping(value = "/api/likepost", method = RequestMethod.POST)
    @ResponseBody
    public Result likepost(@RequestParam("post_id")int post_id,
                           @RequestParam("username")String username){
        //utilize UTC time as like_time
        int flag = iPostService.likepost(post_id,username);
        String msg;
        if(flag == 1)
            msg = "like successfully";
        else
            msg = "cancel like successfully";
        return Result.ok(msg);
    }



    @CrossOrigin
    @ApiOperation(value = "collect post or remove collection, point")
    @RequestMapping(value = "/api/collect", method = RequestMethod.POST)
    @ResponseBody
    public Result collect(@RequestParam("post_id")int post_id,
                          @RequestParam("username")String username){
        int flag = iPostService.collect(post_id,username);
        String msg;
        if(flag == 1)
            msg = "collect successfully";
        else
            msg = "remove collection successfully";
        return Result.ok(msg);
    }

}
