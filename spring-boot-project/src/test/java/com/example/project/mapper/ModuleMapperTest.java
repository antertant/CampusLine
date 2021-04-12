package com.example.project.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ModuleMapperTest {
    @Resource
    private ModuleMapper moduleMapper;

    @Test//quit admin
    void deleteAdmin() {
        Assertions.assertEquals(1,moduleMapper.deleteAdmin("Mao"));
    }

    @Test//get admin list of a module
    void getAdmins(){
        List<String> admins = new ArrayList<>();
        admins.add("h349wu"); admins.add("ppp");admins.add("user1");
        admins.add("zgf");admins.add("zgfu");
        List<String> Admin = moduleMapper.getAdmins("vue");
        Assertions.assertEquals(admins.size(),Admin.size());
        for(int i=0;i< admins.size();i++){
            Assertions.assertEquals(admins.get(i),Admin.get(i));
        }
    }

    //    @Test
//    void gethotmodules() {
//    }

}