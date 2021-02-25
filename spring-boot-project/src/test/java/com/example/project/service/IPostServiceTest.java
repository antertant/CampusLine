package com.example.project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IPostServiceTest {
    @Autowired
    private IPostService iPostService;

//    @Test
//    void testdeletePost1() {
//        //delete post existing
//        Assertions.assertEquals(1,iPostService.deletePost(13));
//    }

    @Test
    void testdeletePost0() {
        //delete post not existing
        Assertions.assertEquals(0,iPostService.deletePost(13));
    }

    @Test
    void testlikepost1() {
        //like post
        Assertions.assertEquals(1,iPostService.likepost(11,"meng"));
    }

    @Test
    void testlikepost2() {
        //cancel like post
        Assertions.assertEquals(2,iPostService.likepost(11,"meng"));
    }

    @Test
    void testgetlikes() {
        //get the like_user list of a post
        List<String> like_users = new ArrayList<>();
        like_users.add("meng");
        like_users.add("ppp");
        like_users.add("Mao");
        Assertions.assertEquals(like_users,iPostService.getlikes(9));
    }

    @Test
    void testcollect2() {
        //cancel collect post
        Assertions.assertEquals(2,iPostService.collect(4,"Mao"));
    }

    @Test
    void testcollect1() {
        //collect post
        Assertions.assertEquals(1,iPostService.collect(4,"Mao"));
    }
}