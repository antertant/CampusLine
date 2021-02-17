package com.example.project.mapper;

import com.example.project.entity.CommentReply;
import com.example.project.entity.PostComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {
    void insertComment(@Param(value = "post_id")int post_id,
                       @Param(value = "username")String username,
                       @Param(value = "content")String content);
    int getPost_id(@Param(value = "comment_id")int comment_id);
    void deleteComment(@Param(value = "comment_id")int comment_id);

    List<PostComment> getComments(@Param(value = "post_id")int post_id);
    int getLikeCount(@Param(value = "comment_id")int comment_id);
    List<CommentReply> getReplies(@Param(value = "comment_id")int comment_id);
    void insertCommentReply(@Param(value = "comment_id")int comment_id,
                            @Param(value = "from_user")String from_user,
                            @Param(value = "to_user")String to_user,
                            @Param(value = "reply_content")String reply_content);

    int existLike(@Param(value = "comment_id")int comment_id,
                  @Param(value = "clike_user")String clike_user);
    void insertCommentLike(@Param(value = "comment_id")int comment_id,
                           @Param(value = "clike_user")String clike_user);
    void deleteCommentLike(@Param(value = "comment_id")int comment_id,
                           @Param(value = "clike_user")String clike_user);
}
