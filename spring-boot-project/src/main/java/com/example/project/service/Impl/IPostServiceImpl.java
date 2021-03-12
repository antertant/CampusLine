package com.example.project.service.Impl;

import com.example.project.entity.Post;
import com.example.project.entity.PostComment;
import com.example.project.entity.PostLike;
import com.example.project.mapper.ModuleMapper;
import com.example.project.mapper.PostMapper;
import com.example.project.service.IPostService;
import com.example.project.utils.HTMLUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IPostServiceImpl implements IPostService {
    //choice=1 like choice=-1 cancel like
    //choice=2 comment choice=-2 delete comment
    //choice=3 collect choice=-3 remove collection
    @Resource
    private PostMapper postMapper;
    @Resource
    private ModuleMapper moduleMapper;
    @Override
    public void insertPost(Post post){
        Post post_ = HTMLUtils.escape(post);
        postMapper.insertPost(post_);
    }

    @Override
    public int deletePost(int post_id){
        int flag = postMapper.deletePost(post_id);
        return flag;
    }

    @Override
    public int likepost(int post_id, String username){
        int choice;//like or cancel like
        int existlike = postMapper.existLike(post_id,username);
        if(existlike == 0){
            choice = 1;
            postMapper.insertLike(post_id,username);//and like time
            postMapper.updateP(post_id,choice);//likes++ at table post
            return 1;
        }
        else{
            choice = -1;
            postMapper.deleteLike(post_id,username);//delete record at post_like table
            postMapper.updateP(post_id,choice);//likes-- at post table
//            postMapper.updatePoint(author,module_name,-eachlikepoint);
            return 2;
        }
    }

    @Override
    public List<String> getlikes(int post_id){
        List<String> likes = postMapper.getLikes(post_id);
        return likes;
    }


    @Override
    public int collect(int post_id,String username){
        int choice;//collect or remove collect
        int existcollect = postMapper.existCollect(post_id,username);
        if(existcollect == 0){
            choice = 3;
            postMapper.insertCollect(post_id,username);
            postMapper.updateP(post_id,choice);
            return 1;
        }
        else{
            choice = -3;
            postMapper.deleteCollect(post_id,username);
            postMapper.updateP(post_id,choice);
//            postMapper.updatePoint(author,module_name,-eachcollectpoint);
            return 2;
        }
    }

    @Override
    public List<Post> getCollects(String username){
        List<Post> collects= postMapper.getCollects(username);
        return collects;
    }

    @Override
    public int countCollect(String username){
        return postMapper.countCollect(username);
    }

    @Override
    public List<PostLike> getlikedpost(String username) { return postMapper.getLikedPost(username);}

}
