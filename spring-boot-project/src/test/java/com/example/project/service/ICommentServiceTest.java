package com.example.project.service;

import com.example.project.mapper.CommentMapper;
import com.example.project.mapper.PostMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ICommentServiceTest {
    @Autowired
    private ICommentService iCommentService;
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private PostMapper postMapper;

    @Test
    void commentpost() {
        Assertions.assertEquals(3,(commentMapper.getComments(2)).size());
        Assertions.assertEquals(1,commentMapper.insertComment(2,"meng","comment..."));
        postMapper.updateP(2,2);
        Assertions.assertEquals(4,(commentMapper.getComments(2)).size());
    }


    @Test// like comment or cancel like comment
    void likecomment() {
        Assertions.assertEquals(1,iCommentService.likecomment(5,"Mao"));
        Assertions.assertEquals(0,iCommentService.likecomment(5,"Mao"));
    }

    @Test
    void deletecomment() {
        Assertions.assertEquals(4,(commentMapper.getComments(2)).size());
        Assertions.assertEquals(1,commentMapper.deleteComment(11));
        postMapper.updateP(2,-2);
        Assertions.assertEquals(3,(commentMapper.getComments(2)).size());
    }

}