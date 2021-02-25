package com.example.project.utils;

import com.example.project.entity.Post;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

//process the html from Rich Text Editor
public class HTMLUtils {
    public static Post escape(Post post){
        String content = post.getPost_content();
        String temp = HtmlUtils.htmlEscape(content);
        post.setPost_content(temp);
        return post;
    }

    public static Post tohtml(Post post){
        String content = post.getPost_content();
        String temp = HtmlUtils.htmlUnescape(content);
        post.setPost_content(temp);
        return post;
    }

    public static List<Post> tohtmls(List<Post> posts){
        for(Post p:posts){
            String content = p.getPost_content();
            String temp = HtmlUtils.htmlUnescape(content);
            p.setPost_content(temp);
        }
        return posts;
    }
}
