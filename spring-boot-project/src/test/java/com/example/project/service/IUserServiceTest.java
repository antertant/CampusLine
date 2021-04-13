package com.example.project.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.project.entity.User;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class IUserServiceTest {
    @Autowired
    private IUserService iUserService;


    @Test
    void testfollow(){
        //test follow
        Assertions.assertEquals(1,iUserService.follow("ppp","Mao"));
        //test cancel follow
        Assertions.assertEquals(2,iUserService.follow("ppp","Mao"));
    }

    @Test
    void testgetpassword(){
        Assertions.assertEquals("123",iUserService.getpassword("gang"));
    }

    @Test
    void testgetpassword_email(){
        Assertions.assertEquals("123",iUserService.getpassword_email("1@qq.com"));
    }

    @Test
    void testgetfollower(){
        List<String> follower=new ArrayList<>();
        follower.add("meng");
        Assertions.assertEquals(follower,iUserService.getFollower("Mao"));
    }

    @Test
    void testcountfollower(){
        Assertions.assertEquals(1,iUserService.countFollower("Mao"));
    }

    @Test
    void testgetfollow(){
        List<String> follow=new ArrayList<>();
        follow.add("Mao");
        Assertions.assertEquals(follow,iUserService.getFollow("meng"));
    }

    @Test
    void testcountfollow(){
        Assertions.assertEquals(1,iUserService.countFollow("meng"));
    }

    @Test
    void testisfollowed(){
        Assertions.assertEquals(1,iUserService.isFollowed("meng","Mao"));
    }

    @Test//follow
    void testfollow0(){Assertions.assertEquals(1,iUserService.follow("ppp","Mao"));}

    @Test//unfollow
    void testfollow1(){Assertions.assertEquals(2,iUserService.follow("ppp","Mao"));}

}
