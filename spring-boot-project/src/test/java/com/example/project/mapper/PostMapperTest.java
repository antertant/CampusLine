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
        String s = "2023-02-18 00:31:19";
        Date date = format.parse(s);
        post.setPost_createtime(date);

        post.setPost_likes(3);
        post.setPost_comments(0);
        post.setPost_collections(0);
        Assertions.assertEquals(post,postMapper.getPost(7));
    }

    @Test//search posts by key
    void searchPosts() throws ParseException{

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
        List<Post> list1 = postMapper.getPostsByAuthor("ppp");
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