package com.example.project.utils;

import com.example.project.entity.Post;
import com.example.project.mapper.PostMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HTMLUtilsTest {
    @Resource
    private PostMapper postMapper;

    @Test
    void tohtml_eacape() {
        Post post = postMapper.getPost(15);

        Post htmlpost = HTMLUtils.tohtml(post);
        String content_html = htmlpost.getPost_content();
        Post escapepost = HTMLUtils.escape(htmlpost);
        String content_escape = escapepost.getPost_content();

        Assertions.assertEquals("<title>hello</title>",content_html);
        Assertions.assertEquals("&lt;title&gt;hello&lt;/title&gt;",content_escape);
    }

}