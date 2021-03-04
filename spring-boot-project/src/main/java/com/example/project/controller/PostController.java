package com.example.project.controller;

import com.example.project.entity.Post;
import com.example.project.entity.PostComment;
import com.example.project.mapper.PostMapper;
import com.example.project.result.Result;
import com.example.project.service.IPostService;
import com.example.project.utils.HTMLUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class PostController {
    @Autowired
    private IPostService iPostService;
    @Resource
    private PostMapper postMapper;

    @CrossOrigin
    @ApiOperation("post a new post")
    @RequestMapping(value = "/api/createpost", method = RequestMethod.POST)
    @ResponseBody
    public Result createpost(@RequestBody Post post){
        iPostService.insertPost(post);
        return Result.ok("post successfully");
    }

    @CrossOrigin
    @ApiOperation("get post by its id")
    @RequestMapping(value = "/api/getpost", method = RequestMethod.GET)
    @ResponseBody
    public Result getpost(@RequestParam("post_id")int post_id){
        Post post = postMapper.getPost(post_id);
        return Result.ok(HTMLUtils.tohtml(post));
    }

    @CrossOrigin
    @ApiOperation("get posts by their author")
    @RequestMapping(value = "/api/getpostbyauthor", method = RequestMethod.GET)
    @ResponseBody
    public Result getpostsbyauthor(@RequestParam("username")String username){
        List<Post> posts = postMapper.getPostsbyAuthor(username);
        return Result.ok(HTMLUtils.tohtmls(posts));
    }

    @CrossOrigin
    @ApiOperation("get posts with search key")
    @RequestMapping(value = "/api/searchpost", method = RequestMethod.GET)
    @ResponseBody
    public Result searchpost(@RequestParam("key")String key){
        List<Post> posts = postMapper.searchPosts(key);
        return Result.ok(HTMLUtils.tohtmls(posts));
    }

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

    @CrossOrigin
    @ApiOperation("get the collect_list of a user")
    @RequestMapping(value = "/api/getcollects", method = RequestMethod.GET)
    @ResponseBody
    public Result getcollects(@RequestParam("username")String username){
        List<Post> collects = iPostService.getCollects(username);
        return Result.ok(HTMLUtils.tohtmls(collects));
    }

    @CrossOrigin
    @ApiOperation("count the user's collections")
    @RequestMapping(value = "/api/countcollects", method = RequestMethod.GET)
    @ResponseBody
    public Result countcollect(@RequestParam("username")String username){
        int countcollect=iPostService.countCollect(username);
        return Result.ok(countcollect);

    }

    @CrossOrigin
    @ApiOperation("get followed users' 10 newest post")
    @RequestMapping(value = "/api/getfollowpost", method = RequestMethod.GET)
    @ResponseBody
    public Result getfollowpost(@RequestParam("follower")String follower){
        List<Post> followposts = postMapper.getFollowPost(follower);
        return Result.ok(HTMLUtils.tohtmls(followposts));
    }

}
