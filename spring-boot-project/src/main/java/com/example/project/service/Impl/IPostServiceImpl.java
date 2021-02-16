package com.example.project.service.Impl;

import com.example.project.entity.Post;
import com.example.project.mapper.ModuleMapper;
import com.example.project.mapper.PostMapper;
import com.example.project.service.IPostService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
    public int likepost(int post_id, String username){
        int choice;//like or cancel like
//        int eachlikepoint = 2;
//        Post post = postMapper.getPost(post_id);
//        String module_name = post.getModule_name();
//        String author = post.getPost_author();
//        if(module_name == null)
//            module_name = "life";
        int existlike = postMapper.existLike(post_id,username);
        if(existlike == 0){
            choice = 1;
            postMapper.insertLike(post_id,username);//and like time
            postMapper.updateP(post_id,choice);//likes++ at table post
//            int existpoint = moduleMapper.existPoint(author,module_name);
//            if(existpoint == 0){//no entry at module_point table
//                moduleMapper.insertPoint(author,module_name,eachlikepoint);
//            }
//            else{//update author's point at module_name
//                postMapper.updatePoint(author,module_name,eachlikepoint);
//            }

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
    public void commentpost(int post_id, String username, String content){
        postMapper.insertComment(post_id,username,content);
        postMapper.updateP(post_id,2);
    }
    @Override
    public void deletecomment(int comment_id){
        postMapper.updateP(postMapper.getPost_id(comment_id),-2);
        postMapper.deleteComment(comment_id);
    }

    @Override
    public int collect(int post_id,String username){
        int choice;//collect or remove collect
//        int eachcollectpoint = 3;
//        Post post = postMapper.getPost(post_id);
//        String module_name = post.getModule_name();
//        String author = post.getPost_author();
//        if(module_name == null)
//            module_name = "life";
        int existcollect = postMapper.existCollect(post_id,username);
        if(existcollect == 0){
            choice = 3;
            postMapper.insertCollect(post_id,username);
            postMapper.updateP(post_id,choice);
//            int existpoint = moduleMapper.existPoint(author,module_name);
//            if(existpoint == 0){
//                moduleMapper.insertPoint(author,module_name,eachcollectpoint);
//            }
//            else{
//                postMapper.updatePoint(author,module_name,eachcollectpoint);
//            }
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


}
