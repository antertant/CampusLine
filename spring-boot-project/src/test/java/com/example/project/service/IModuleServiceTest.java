package com.example.project.service;

import com.example.project.entity.Post;
import com.example.project.entity.Module;
import com.example.project.entity.User;
import com.example.project.mapper.ModuleMapper;
import com.example.project.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IModuleServiceTest {
    @Autowired
    private IModuleService iModuleService;
    @Resource
    private ModuleMapper moduleMapper;

    @Test//get posts of a module
    void getPosts() throws ParseException {
        List<Post> list = new ArrayList<>();
        Post post = new Post();
        post.setPost_id(3);
        post.setModule_name("vue");
        post.setPost_content("vue vuevue vue");
        post.setPost_author("ppp");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = "2021-02-05 22:28:24";
        Date date = format.parse(s);
        post.setPost_createtime(date);
        post.setPost_likes(1);
        post.setPost_comments(0);
        post.setPost_collections(0);
        list.add(post);

        Assertions.assertEquals(list,iModuleService.getPosts("vue"));
    }

    @Test//modules of know
    void getModules() {
        List<Module> list = iModuleService.getModules();
        List<Module> list1 = new ArrayList<>();
        Module module3 = new Module();
        module3.setModule_name("django");
        module3.setModule_intro("default module introduction");
        module3.setMax_adminNumber(5);

        Module module0 = new Module();
        module0.setModule_name("java");
        module0.setModule_intro("Java Is the Language of Possibilities. Java is powering the innovation behind our digital world. Harness this potential with Java resources for student coders, hobbyists, developers, and IT leaders.");
        module0.setMax_adminNumber(5);

        Module module1 = new Module();
        module1.setModule_name("springboot");
        module1.setModule_intro("Spring Boot offers a fast way to build applications. It looks at your classpath and at the beans you have configured, makes reasonable assumptions about what you are missing, and adds those items. With Spring Boot, you can focus more on business features and less on infrastructure.");
        module1.setMax_adminNumber(5);

        Module module2 = new Module();
        module2.setModule_name("vue");
        module2.setModule_intro("Vue (pronounced /vjuː/, like view) is a progressive framework for building user interfaces. It is designed from the ground up to be incrementally adoptable, and can easily scale between a library and a framework depending on different use cases. It consists of an approachable core library that focuses on the view layer only, and an ecosystem of supporting libraries that helps you tackle complexity in large Single-Page Applications.");
        module2.setMax_adminNumber(5);
        list1.add(module3);;list1.add(module0); list1.add(module1); list1.add(module2);
        Assertions.assertEquals(list1.size(),list.size());
        for(int i=0;i<list.size();i++){
            Assertions.assertEquals(list1.get(i),list.get(i));
        }

    }

    @Test//request to create a module
    void createModule(){
        int res = iModuleService.createModule("vue");
        Assertions.assertEquals(1,res);
        int res1 = iModuleService.createModule("h");
        Assertions.assertEquals(0,res1);
    }

    @Test//search know modules
    void searchModule() {
        List<Module> list = iModuleService.searchModule("e");
        List<Module> list1 = new ArrayList<>();

        Module module1 = new Module();
        module1.setModule_name("vue");
        module1.setModule_intro("Vue (pronounced /vjuː/, like view) is a progressive framework for building user interfaces. It is designed from the ground up to be incrementally adoptable, and can easily scale between a library and a framework depending on different use cases. It consists of an approachable core library that focuses on the view layer only, and an ecosystem of supporting libraries that helps you tackle complexity in large Single-Page Applications.");
        module1.setMax_adminNumber(5);
        list1.add(module1);
        Assertions.assertEquals(list.size(),list1.size());
        for(int i=0;i<list.size();i++){
            Assertions.assertEquals(list.get(i),list1.get(i));
        }
    }

    @Test
    void getPoints() {
        //
        Assertions.assertEquals(2,iModuleService.getPoints("ppp","life"));
    }

    @Test//apply to be an admin of a module
    void applym() {
        //you have been manager of some module
        Assertions.assertEquals(1,iModuleService.applym("meng","java"));
        //the module has had enough managers
        Assertions.assertEquals(2,iModuleService.applym("Mao","vue"));
        //dont have enough points to apply for a manager
        Assertions.assertEquals(3,iModuleService.applym("user3","springboot"));
        //apply successfully
        //Assertions.assertEquals(0,iModuleService.applym("Mao","springboot"));
    }

    @Test
    void editintro() {
        iModuleService.editintro("life","new life_intro");
        String intro = moduleMapper.getModule("life").getModule_intro();
        Assertions.assertEquals("new life_intro",intro);
    }

//    @Test
//    void gethotmodules() {
//
//    }
}