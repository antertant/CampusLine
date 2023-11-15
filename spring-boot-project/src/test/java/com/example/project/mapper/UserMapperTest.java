package com.example.project.mapper;
import com.example.project.entity.ChatMessage;
import com.example.project.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    void updatepassword(){
        userMapper.updatePassword("zgf","1234");
        Assertions.assertEquals("1234",userMapper.selectByPrimaryKey("zgf").getPassword());
        userMapper.updatePassword("zgf","123");
    }

    @Test
    void insertUser(){
        userMapper.insertUser("zzz","111","123123");
        User user=new User();
        user.setUsername("zzz");
        user.setPassword("111");
        user.setEmail("123123");
        Assertions.assertEquals(userMapper.selectByPrimaryKey("zzz").getUsername(),user.getUsername());
        Assertions.assertEquals(userMapper.selectByPrimaryKey("zzz").getPassword(),user.getPassword());
        Assertions.assertEquals(userMapper.selectByPrimaryKey("zzz").getEmail(),user.getEmail());
        userMapper.deleteUser("zzz");
    }

    @Test
    void deleteUser(){
        userMapper.insertUser("zzz","111","123123");
        userMapper.deleteUser("zzz");
        Assertions.assertEquals(0,userMapper.existUser("zzz"));
    }

    @Test
    void existUser(){
        Assertions.assertEquals(1,userMapper.existUser("zgfu"));
        Assertions.assertEquals(0,userMapper.existUser("xxx"));
    }

    @Test
    void existEmail(){
        Assertions.assertEquals(1,userMapper.existEmail("1173229585@qq.com"));
        Assertions.assertEquals(0,userMapper.existEmail("q123123123"));
    }

    @Test
    void existVemail(){
        Assertions.assertEquals(1,userMapper.existVerifyEmail("1173229585@qq.com"));
        Assertions.assertEquals(0,userMapper.existVerifyEmail("1173229"));
    }

    @Test
    void createVcode(){
        userMapper.createVerifyCode("123123","1234");
        Assertions.assertEquals(1,userMapper.checkVerifyCode("123123","1234"));
        userMapper.deleteVerifyCode("123123");
    }

    @Test
    void checkVcode(){
        Assertions.assertEquals(1,userMapper.checkVerifyCode("1234@qq.com","1234"));
        Assertions.assertEquals(0,userMapper.checkVerifyCode("1234@qq.com","123"));
        Assertions.assertEquals(0,userMapper.checkVerifyCode("124@qq.com","1234"));
    }

    @Test
    void getallnewchat(){
        List<ChatMessage> chat=userMapper.getAllNewChat("Mao");
        Assertions.assertEquals("hi!",chat.get(0).getContent());
    }

    @Test
    void countunreadchat_all(){
        Assertions.assertEquals(2,userMapper.countUnreadChat_All("Mao"));
    }

    @Test
    void countunreadchat(){
        Assertions.assertEquals(1,userMapper.countUnreadChat("Mao","gang"));
    }

    @Test
    void getchat(){
        List<ChatMessage> chat=userMapper.getChat("Mao","gang");
        String s=chat.get(0).getContent();
        Assertions.assertEquals("dsss",s);

        userMapper.setUnread1("Mao","gang");
        userMapper.setUnread1("gang","Mao");
    }

    @Test
    void setunread1(){
        userMapper.setUnread1("Mao","gang");
        List<ChatMessage> chat=userMapper.getChat("Mao","gang");
        int s=chat.get(0).getUnread();
        Assertions.assertEquals(1,s);

        userMapper.setUnread1("Mao","gang");
        userMapper.setUnread1("gang","Mao");

    }
}
