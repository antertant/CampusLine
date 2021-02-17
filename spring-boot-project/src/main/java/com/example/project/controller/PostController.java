package com.example.project.controller;

import com.example.project.result.Result;
import com.example.project.service.IPostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    private IPostService iPostService;

    @CrossOrigin
    @ApiOperation("delete post")
    @RequestMapping(value = "/api/deletepost", method = RequestMethod.POST)
    @ResponseBody
    public Result deletepost(@RequestParam("post_id")int post_id){
        int flag = iPostService.deletePost(post_id);
        if(flag == 1)
            return Result.ok("delete successfully");
        else
            return Result.fail("delete failed");
    }

    @CrossOrigin
    @ApiOperation("like or cancel like")
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
    @ApiOperation("get the like_list of a post")
    @RequestMapping(value = "/api/getlikes", method = RequestMethod.GET)
    @ResponseBody
    public Result getlikes(@RequestParam("post_id")int post_id){
        List<String> likes = iPostService.getlikes(post_id);
        return Result.ok(likes);
    }

    @CrossOrigin
    @ApiOperation(value = "comment post")
    @RequestMapping(value = "/api/comment", method = RequestMethod.POST)
    @ResponseBody
    public Result comment(@RequestParam("post_id")int post_id,
                          @RequestParam("username")String username,
                          @RequestParam("content")String content){
        iPostService.commentpost(post_id,username,content);
        return Result.ok("comment successfully");
    }

    @CrossOrigin
    @ApiOperation(value = "delete comment")
    @RequestMapping(value = "/api/deletecomment", method = RequestMethod.POST)
    @ResponseBody
    public Result deletecomment(@RequestParam("comment_id")int comment_id){
        iPostService.deletecomment(comment_id);
        return Result.ok("delete successfully");
    }

    @CrossOrigin
    @ApiOperation(value = "collect post or remove collection")
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


//    @CrossOrigin
//    @ApiOperation(value = "show hot posts in life module")
//    @RequestMapping(value = "/api/getlifehot",method = RequestMethod.GET)
//    @ResponseBody
//    public Result getlifehot(){
//
//    }


}
