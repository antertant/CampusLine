package com.example.project.mapper;

import com.example.project.entity.CommentReply;
import com.example.project.entity.PostComment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentMapperTest {
    @Resource
    private CommentMapper commentMapper;


    @Test//get comments of a post
    void getComments() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<PostComment> list = commentMapper.getComments(8);
        List<PostComment> list1 = new ArrayList<>();
        PostComment postComment0 = new PostComment();
        postComment0.setComment_id(8);
        postComment0.setPost_id(8);
        postComment0.setComment_content("like like like");
        Date d = format.parse("2021-02-18 11:22:14");
        postComment0.setComment_time(d);

        PostComment postComment1 = new PostComment();
        postComment1.setComment_id(9);
        postComment1.setPost_id(8);
        postComment1.setComment_content("like like like");
        Date d1 = format.parse("2021-02-18 11:24:12");
        postComment1.setComment_time(d1);
        list1.add(postComment0);list1.add(postComment1);

        Assertions.assertEquals(list.size(),list1.size());
        for(int i=0;i<list.size();i++){
            Assertions.assertEquals(list.get(i),list1.get(i));
        }
    }

    @Test//get the like count of a comment
    void getLikeCount() {
        Assertions.assertEquals(2,commentMapper.getLikeCount(5));
    }

    @Test//get replies of a comment
    void getReplies() throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<CommentReply> list = commentMapper.getReplies(4);
        List<CommentReply> list1 = new ArrayList<>();
        CommentReply commentReply = new CommentReply();
        commentReply.setReply_id(2);
        commentReply.setComment_id(4);
        commentReply.setCreply_content("no");
        commentReply.setCreply_time(format.parse("2021-02-17 00:29:57"));
        commentReply.setFrom_user("meng");
        commentReply.setTo_user("ppp");
        list1.add(commentReply);
        Assertions.assertEquals(list.size(),list1.size());
        for(int i=0;i<list.size();i++){
            Assertions.assertEquals(list.get(i),list1.get(i));
        }
    }

    @Test
    void insertCommentReply() {
        int ret = commentMapper.insertCommentReply(9,"Mao","ppp","commentreply",0);
        Assertions.assertEquals(1,ret);
    }

}