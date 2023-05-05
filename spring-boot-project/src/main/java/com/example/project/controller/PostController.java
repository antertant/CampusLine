package com.example.project.controller;

import com.example.project.entity.Post;
import com.example.project.entity.PostLike;
import com.example.project.entity.PostComment;
import com.example.project.entity.NewMsg;
import com.example.project.entity.CommentLike;
import com.example.project.entity.CommentReply;
import com.example.project.mapper.CommentMapper;
import com.example.project.mapper.PostMapper;
import com.example.project.result.Result;
import com.example.project.service.ICommentService;
import com.example.project.service.IPostService;
import com.example.project.utils.HTMLUtils;
import io.swagger.annotations.ApiOperation;
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
    @Autowired
    private ICommentService iCommentService;
    @Resource
    private CommentMapper commentMapper;

    @CrossOrigin
    @ApiOperation("post a new post")
    @RequestMapping(value = "/api/createpost", method = RequestMethod.POST)
    @ResponseBody
    public Result createPost(@RequestBody Post post){
        iPostService.insertPost(post);
        return Result.ok("post successfully");
    }

    @CrossOrigin
    @ApiOperation("edit a post")
    @RequestMapping(value = "/api/editpost", method = RequestMethod.POST)
    @ResponseBody
    public Result editPost(@RequestBody Post post){//post_id,content
        Post post_ = HTMLUtils.escape(post);
        postMapper.updatePost(post_);
        return Result.ok("edit successfully");
    }

    @CrossOrigin
    @ApiOperation("get post by its id")
    @RequestMapping(value = "/api/getpost", method = RequestMethod.GET)
    @ResponseBody
    public Result getPost(@RequestParam("post_id")int post_id){
        Post post = postMapper.getPost(post_id);
        return Result.ok(HTMLUtils.tohtml(post));
    }

    @CrossOrigin
    @ApiOperation("get posts by their author")
    @RequestMapping(value = "/api/getpostbyauthor", method = RequestMethod.GET)
    @ResponseBody
    public Result getPostsByAuthor(@RequestParam("username")String username){
        List<Post> posts = postMapper.getPostsByAuthor(username);
        return Result.ok(HTMLUtils.tohtmls(posts));
    }

    @CrossOrigin
    @ApiOperation("get posts with search key")
    @RequestMapping(value = "/api/searchpost", method = RequestMethod.GET)
    @ResponseBody
    public Result searchPost(@RequestParam("key")String key){
        List<Post> posts = postMapper.searchPosts(key);
        return Result.ok(HTMLUtils.tohtmls(posts));
    }

    @CrossOrigin
    @ApiOperation("delete post")
    @RequestMapping(value = "/api/deletepost", method = RequestMethod.POST)
    @ResponseBody
    public Result deletePost(@RequestParam("post_id")int post_id){
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
    public Result likePost(@RequestParam("post_id")int post_id,
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
    public Result getLikes(@RequestParam("post_id")int post_id){
        List<String> likes = iPostService.getlikes(post_id);
        return Result.ok(likes);
    }

    @CrossOrigin
    @ApiOperation("get counts of 4 unviewed message:likedpost,likedcomment,postccomment,commentreply")
    @RequestMapping(value = "/api/getcountnew", method = RequestMethod.GET)
    @ResponseBody
    public Result getCountNew(@RequestParam("username")String username){
        NewMsg countNew = new NewMsg();
        countNew.setcnewlpost(postMapper.getNew1(username));
        countNew.setcnewlcomment(postMapper.getNew2(username));
        countNew.setcnewpostcomment(postMapper.getNew3(username));
        countNew.setcnewcommentreply(postMapper.getNew4(username));
        return Result.ok(countNew);
    }

    @CrossOrigin
    @ApiOperation("get the new message of a user")
    @RequestMapping(value = "/api/getnewlist", method = RequestMethod.GET)
    @ResponseBody
    public Result getNewList(@RequestParam("username")String username, @RequestParam("choice")Integer choice){
        if (choice==1){
            //choice=1, get the newest list of PostLike
            List<PostLike> likedPost = iPostService.getlikedpost(username);
            postMapper.updateV(username,choice);
            return Result.ok(likedPost);
        }
        else if (choice==2){
            //choice=2, get the newest list of CommentLike
            List<CommentLike> likedComment=iCommentService.getlikedcomment(username);
            postMapper.updateV(username,choice);
            return Result.ok(likedComment);
        }
        else if (choice==3){
            //choice=3, get the newest list of PostComment
            List<PostComment> postComment=commentMapper.getPostComment(username);
            postMapper.updateV(username,choice);
            return Result.ok(postComment);
        }
        else if (choice==4){
            //choice=4, get the newest list of CommentReply
            List<CommentReply> commentReply=commentMapper.getCommentReply(username);
            postMapper.updateV(username,choice);
            return Result.ok(commentReply);
        }
        return Result.ok(0);
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
    public Result getCollects(@RequestParam("username")String username){
        List<Post> collects = iPostService.getCollects(username);
        return Result.ok(HTMLUtils.tohtmls(collects));
    }

    @CrossOrigin
    @ApiOperation("count the user's collections")
    @RequestMapping(value = "/api/countcollects", method = RequestMethod.GET)
    @ResponseBody
    public Result countCollect(@RequestParam("username")String username){
        int countCollect=iPostService.countCollect(username);
        return Result.ok(countCollect);

    }

    @CrossOrigin
    @ApiOperation("get followed users' 10 newest post")
    @RequestMapping(value = "/api/getfollowpost", method = RequestMethod.GET)
    @ResponseBody
    public Result getFollowPost(@RequestParam("follower")String follower){
        List<Post> followPosts = postMapper.getFollowPost(follower);
        return Result.ok(HTMLUtils.tohtmls(followPosts));
    }

}
