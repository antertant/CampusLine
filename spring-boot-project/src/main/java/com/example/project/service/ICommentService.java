package com.example.project.service;

import com.example.project.entity.CommentLike;
import com.example.project.entity.CommentReply;
import com.example.project.entity.PostComment;
import com.example.project.entity.PostLike;

import java.util.List;

public interface ICommentService {
    void commentpost(int post_id, String username, String content);

    //    void commentreply(int comment_id, String username, String reply_content);
    void commentreply(CommentReply reply);
    int likecomment(int comment_id,String clike_user);

    void deletecomment(int comment_id);
    List<PostComment> getcomments(int post_id);
    List<CommentLike> getlikedcomment(String username);

}
