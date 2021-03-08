package com.example.project.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ModuleMapperTest {
    @Resource
    private ModuleMapper moduleMapper;

    @Test//quit admin
    void deleteAdmin() {
        Assertions.assertEquals(1,moduleMapper.deleteAdmin("Mao"));
    }

//    @Test
//    void gethotmodules() {
//    }

}