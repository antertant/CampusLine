package com.example.project.mapper;

import com.example.project.entity.Post;
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
class PostMapperTest {
    @Resource
    private PostMapper postMapper;

//    @Test
//    void insertPost() {
//    }
    @Test//edit post
    void editPost(){
        Post p = postMapper.getPost(4);
        p.setPost_content("Test");
        postMapper.updatePost(p);
        Post p1 = postMapper.getPost(4);
        Assertions.assertEquals("Test",p1.getPost_content());
    }

    @Test//get a post by its id
    void getPost() throws ParseException {
        Post post = new Post();
        post.setPost_id(7);
        post.setModule_name("springboot");
        post.setPost_content("spspspspsp");
        post.setPost_author("Mao");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = "2021-02-18 00:31:19";
        Date date = format.parse(s);
        post.setPost_createtime(date);

        post.setPost_likes(3);
        post.setPost_comments(0);
        post.setPost_collections(0);
        Assertions.assertEquals(post,postMapper.getPost(7));
    }

    @Test//search posts by key
    void searchPosts() throws ParseException{
//        Post post0 = new Post();
//        Post post1 = new Post();
//        post0.setPost_id(7);
//        post0.setModule_name("springboot");
//        post0.setPost_content("spspspspsp");
//        post0.setPost_author("Mao");
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String s0 = "2021-02-18 00:31:19";
//        Date date0 = format.parse(s0);
//        post0.setPost_createtime(date0);
//        post0.setPost_likes(3);
//        post0.setPost_comments(0);
//        post0.setPost_collections(0);
//
//        post1.setPost_id(2);
//        post1.setModule_name("springboot");
//        post1.setPost_content("springboot is perfect");
//        post1.setPost_author("meng");
//        String s1 = "2021-02-05 22:28:00";
//        Date date1 = format.parse(s1);
//        post1.setPost_createtime(date1);
//        post1.setPost_likes(2);
//        post1.setPost_comments(3);
//        post1.setPost_collections(0);
//
//        List<Post> list = new ArrayList<>();
//        list.add(post0);
//        list.add(post1);
        List<Integer> list = new ArrayList<>();
        list.add(7); list.add(2);
        List<Post> list1 = postMapper.searchPosts("sp");
        Assertions.assertEquals(list.size(),list1.size());
        for(int i=0;i<list.size();i++){
            Assertions.assertEquals(list.get(i),(list1.get(i)).getPost_id());
        }
    }

    @Test//get posts of a user at a module
    void getPosts() throws ParseException{
        //(9, NULL, 'sohai', 'ppp', '2021-02-18 11:15:43', 3, 0, 0);
        //(12, NULL, 'wuhu', 'ppp', '2021-02-19 00:20:53', 0, 0, 0);
//        Post post0 = new Post();
//        Post post1 = new Post();
//        post0.setPost_id(12);
//        post0.setModule_name(null);
//        post0.setPost_content("wuhu");
//        post0.setPost_author("ppp");
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String s0 = "2021-02-18 19:20:53";
//        Date date0 = format.parse(s0);
//        post0.setPost_createtime(date0);
//        post0.setPost_likes(0);
//        post0.setPost_comments(0);
//        post0.setPost_collections(0);
//
//        post1.setPost_id(9);
//        post1.setModule_name(null);
//        post1.setPost_content("sohai");
//        post1.setPost_author("ppp");
//        String s1 = "2021-02-18 06:15:43";
//        Date date1 = format.parse(s1);
//        post1.setPost_createtime(date1);
//        post1.setPost_likes(3);
//        post1.setPost_comments(0);
//        post1.setPost_collections(0);

//        List<Post> list = new ArrayList<>();
//        list.add(post0);
//        list.add(post1);

        List<Integer> list = new ArrayList<>();
        list.add(8); list.add(6);
        List<Post> list1 = postMapper.getPosts("meng","java");
        Assertions.assertEquals(list.size(),list1.size());
        for(int i=0;i<list.size();i++){
            Assertions.assertEquals(list.get(i),(list1.get(i)).getPost_id());
        }
    }

    @Test
    void existLike() {
        Assertions.assertEquals(1,postMapper.existLike(2,"Mao"));
        Assertions.assertEquals(0,postMapper.existLike(2,"ppp"));
    }

    @Test
    void test_getpostbyauthor(){
        List<Integer> list = new ArrayList<>();
        list.add(12); list.add(9);list.add(3);
        List<Post> list1 = postMapper.getPostsbyAuthor("ppp");
        Assertions.assertEquals(list.size(),list1.size());
        for(int i=0;i<list.size();i++){
            Assertions.assertEquals(list.get(i),(list1.get(i)).getPost_id());
        }
    }

    @Test
    void test_getfollowpost(){
        List<Integer> list = new ArrayList<>();
        list.add(12); list.add(9);list.add(3);
        List<Post> list1 = postMapper.getFollowPost("Mao");
        Assertions.assertEquals(list.size(),list1.size());
        for(int i=0;i<list.size();i++){
            Assertions.assertEquals(list.get(i),(list1.get(i)).getPost_id());
        }
    }

    @Test
    void test_countcollect(){
        Assertions.assertEquals(1,postMapper.countCollect("Mao"));
    }

    @Test
    void test_getNew1(){
        Assertions.assertEquals(3,postMapper.getNew1("Mao"));
    }

    @Test
    void test_getNew2(){
        Assertions.assertEquals(2,postMapper.getNew2("Mao"));
    }

    @Test
    void test_getNew3(){
        Assertions.assertEquals(0,postMapper.getNew3("Mao"));
    }

    @Test
    void test_getNew4(){
        Assertions.assertEquals(3,postMapper.getNew4("Mao"));
    }

    @Test
    void test_getcollect(){
        List<Integer> list = new ArrayList<>();
        list.add(4);
        List<Post> list1 = postMapper.getCollects("Mao");
        Assertions.assertEquals(list.size(),list1.size());
        for(int i=0;i<list.size();i++){
            Assertions.assertEquals(list.get(i),(list1.get(i)).getPost_id());
        }
    }


}