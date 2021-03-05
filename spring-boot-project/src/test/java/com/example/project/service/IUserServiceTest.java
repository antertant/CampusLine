package com.example.project.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class IUserServiceTest {
    @Autowired
    private IUserService iUserService;

    @Test
    void testfollow(){Assertions.assertEquals(1,iUserService.follow("ppp","Mao"));}

}
