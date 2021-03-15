package com.example.project.service.Impl;

import com.example.project.entity.CommentReply;
import com.example.project.entity.CommentLike;
import com.example.project.entity.PostComment;
import com.example.project.mapper.CommentMapper;
import com.example.project.mapper.PostMapper;
import com.example.project.service.ICommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class ICommentServiceImpl implements ICommentService{
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private PostMapper postMapper;

    @Override
    public void commentpost(int post_id, String username, String content){
        commentMapper.insertComment(post_id,username,content);
        postMapper.updateP(post_id,2);//post-->post_comments+1

    }

    @Override
    public void commentreply(CommentReply reply){
        int comment_id = reply.getComment_id();
        String from_user = reply.getFrom_user();
        String to_user = reply.getTo_user();
        String reply_content = reply.getCreply_content();
        int viewed=reply.getViewed();
        commentMapper.insertCommentReply(comment_id,from_user,to_user,reply_content,viewed);
    }

    @Override
    public int likecomment(int comment_id,String clike_user){
        int existCommentlike = commentMapper.existLike(comment_id,clike_user);
        if(existCommentlike==0){
            commentMapper.insertCommentLike(comment_id,clike_user);
            return 0;//like comment
        }
        else{
            commentMapper.deleteCommentLike(comment_id,clike_user);
            return 1;//cancel like comment
        }
    }

    @Override
    public void deletecomment(int comment_id){
        postMapper.updateP(commentMapper.getPost_id(comment_id),-2);
        commentMapper.deleteComment(comment_id);//post-->post_comment-1
    }

    @Override
    public List<PostComment> getcomments(int post_id){
        List<PostComment> comments = commentMapper.getComments(post_id);
        for(PostComment c:comments){
            int c_id = c.getComment_id();
            int like_count = commentMapper.getLikeCount(c_id);
            List<CommentReply> replies = commentMapper.getReplies(c_id);
            c.setComment_likes(like_count);
            c.setReplies(replies);
        }
        Collections.sort(comments);
        return comments;
    }
    @Override
    public List<CommentLike> getlikedcomment(String username){
        return commentMapper.getLikedComment(username);
    }

}
