package com.example.project.controller;

import com.example.project.entity.CommentReply;
import com.example.project.entity.PostComment;
import com.example.project.mapper.CommentMapper;
import com.example.project.result.Result;
import com.example.project.service.ICommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private ICommentService iCommentService;
    @Resource
    private CommentMapper commentMapper;

    @CrossOrigin
    @ApiOperation(value = "comment post")
    @RequestMapping(value = "/api/comment", method = RequestMethod.POST)
    @ResponseBody
    public Result comment(@RequestParam("post_id")int post_id,
                          @RequestParam("username")String username,
                          @RequestParam("content")String content){
        iCommentService.commentpost(post_id,username,content);
        return Result.ok("comment successfully");
    }

    @CrossOrigin
    @ApiOperation(value = "reply comment/reply")
    @RequestMapping(value = "/api/likecomment", method = RequestMethod.POST)
    @ResponseBody
    public Result likecomment(@RequestParam("comment_id")int comment_id,
                              @RequestParam("clike_user")String clike_user){
        int flag = iCommentService.likecomment(comment_id,clike_user);//or cancel
        if(flag == 0)
            return Result.ok("like this comment successfully");
        else
            return Result.ok("cancel successfully");
    }

    @CrossOrigin
    @ApiOperation(value = "reply comment/reply")
    @RequestMapping(value = "/api/commentreply", method = RequestMethod.POST)
    @ResponseBody
    public Result commentreply(@RequestBody CommentReply reply){
        iCommentService.commentreply(reply);
        return Result.ok("reply successfully");
    }

    @CrossOrigin
    @ApiOperation(value = "reply comment/reply")
    @RequestMapping(value = "/api/deletereply", method = RequestMethod.POST)
    @ResponseBody
    public Result deletereply(@RequestParam("reply_id")int reply_id){
        int ret = commentMapper.deleteReply(reply_id);
        if (ret==1)
            return Result.ok("delete reply successfully");
        else
            return Result.fail("delete reply failed");
    }


    @CrossOrigin
    @ApiOperation(value = "delete comment")
    @RequestMapping(value = "/api/deletecomment", method = RequestMethod.POST)
    @ResponseBody
    public Result deletecomment(@RequestParam("comment_id")int comment_id){
        iCommentService.deletecomment(comment_id);
        return Result.ok("delete successfully");
    }

    @CrossOrigin
    @ApiOperation("get the comment_list&replies&comment_likes of a post")
    @RequestMapping(value = "/api/getcomments", method = RequestMethod.GET)
    @ResponseBody
    public Result getcomments(@RequestParam("post_id")int post_id){
        List<PostComment> comments = iCommentService.getcomments(post_id);
        return Result.ok(comments);
    }


}
