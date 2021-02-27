package com.example.project.controller;

import com.example.project.result.Result;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ModuleControllerTest {
    @Autowired
    private ModuleController moduleController;

    @Test
    void getRole() {
        Result ret = moduleController.getRole("user2","vue");
        String s = (String) ret.getData();
        Assertions.assertEquals("admin",s);

        Result ret1 = moduleController.getRole("user2","springboot");
        String s1 = (String) ret1.getData();
        Assertions.assertEquals("user",s1);
    }
}