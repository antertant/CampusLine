package com.example.project.service.Impl;

import com.example.project.entity.HotModule;
import com.example.project.entity.Module;
import com.example.project.entity.Post;
import com.example.project.entity.User;
import com.example.project.mapper.ModuleMapper;
import com.example.project.mapper.PostMapper;
import com.example.project.mapper.UserMapper;
import com.example.project.service.IModuleService;
import com.example.project.utils.HTMLUtils;
import com.example.project.utils.HashmapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IModuleServiceImpl implements IModuleService {
    @Resource
    private ModuleMapper moduleMapper;
    @Resource
    private PostMapper postMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public int createModule(String module_name){
        if(moduleMapper.getModule(module_name)==null){
            //...
            if(moduleMapper.existModule(module_name)==0)
                moduleMapper.createModuleRequest(module_name);
            else
                moduleMapper.updateModuleRequest(module_name);
            return 0;
        }
        else
            return 1;
    }

    @Override
    public List<Post> getPosts(String module_name){
        return moduleMapper.getPosts(module_name);
    }

    @Override
    public int toTop(int post_id){
        int if_top = moduleMapper.ifTop(post_id);
        if(if_top==1){
            moduleMapper.setTop(post_id);
            return 2;
        }
        else{
            String module_name = (postMapper.getPost(post_id)).getModule_name();
            //the number of top posts at a module
            int count = moduleMapper.getTops(module_name);
            if(count>=3)
                return 1;
            else{
                moduleMapper.setTop(post_id);
                return 0;
            }
        }
    }

    @Override
    public List<Module> getModules(){
        return moduleMapper.getModules();
    }

    @Override
    public List<Module> searchModule(String key){
        return moduleMapper.searchModule(key);
    }

    @Override
    public int getPoints(String username,String module_name){
        int points = 0;
        List<Post> posts = postMapper.getPosts(username,module_name);
        for(Post p:posts){
            int likes = p.getPost_likes();
            int collects = p.getPost_collections();
            int comments = p.getPost_comments();
            points += (likes*2 + comments + collects*3)/2;
        }
        return points;
    }

    @Override
    public int applyAdmin(String username, String module_name){
        int leastpoint = 1000;//minimum points to become an admin
        User user = userMapper.selectByPrimaryKey(username);
        int managers = moduleMapper.getAdmins(module_name).size();
        int maxadmins = moduleMapper.getModule(module_name).getMax_adminNumber();
        if(user.getModule_admin()!=null && !Objects.equals(user.getModule_admin(), "")){
            return 1;//you have been manager of some module
        }
        else if(managers>=maxadmins){
            return 2;//the module has had enough managers
        }

        else if(this.getPoints(username,module_name)<leastpoint){
            return 3;//don't have enough points to apply for a manager
        }
        else{
//            ModuleManagement mm = new ModuleManagement();
//            mm.setModule_name(module_name);
            moduleMapper.setAdmin(username,module_name);
//            moduleMapper.insertManagement(mm);
//            moduleMapper.updateUserpermission(username,mm.getPermission_id());
            return 0;
        }
    }


    @Override
    public void quitAdmin(String username){
        moduleMapper.deleteAdmin(username);
    }

    @Override
    public void editIntro(String module_name, String new_intro){
        moduleMapper.editIntro(module_name,new_intro);
    }


    public List<Post> getHots(String module_name, int count){
        /*
        get hotPosts by module_name
        @param module_name
        @param count: Posts count
        @return List<Post>: a list content HotPosts
        */
        // get all posts by module_name
        List<Post> posts = moduleMapper.getPosts(module_name);

        //"post_id": int
        // "like_count/comment_count"-"count(1)":int
        //get like_count/comment_count by all posts in module_name
        List<HashMap<String,Object>> likeCounts = moduleMapper.postLikeCount(module_name);
        List<HashMap<String,Object>> commentCounts = moduleMapper.postCommentCount(module_name);

        //take list convert to map
        //postId : likeCount/commentCount
        HashMap<Integer,Integer> likeCountMap = HashmapUtils.trans(likeCounts);
        HashMap<Integer,Integer> commentCountMap = HashmapUtils.trans(commentCounts);

        //post_id:total_like & comment & reply_counts within 24hrs
        HashMap<Integer,Integer> hashMap = new HashMap<>();
        for (Post post : posts) {
            int post_id = post.getPost_id();
            int like_count = (likeCountMap.get(post_id) == null) ? 0 : likeCountMap.get(post_id);
            int comment_count = (commentCountMap.get(post_id) == null) ? 0 : commentCountMap.get(post_id);
            int reply_count = moduleMapper.commentReplyCount(post_id);
            int total_count = like_count + comment_count + reply_count;
            hashMap.put(post_id, total_count);
        }
        //post_id:count
        //sort posts map and put id & post to the list
        List<Post> hotPosts = HashmapUtils.sort(hashMap, count).stream()
                .map(entry -> postMapper.getPost(entry.getKey()))
                .collect(Collectors.toList());
        return HTMLUtils.tohtmls(hotPosts);

    }

    @Override
    public List<HotModule> getHotModules(){
        int hot_posts = 5;
        int know_hot_modules = 4;
        List<HotModule> hotModules = new ArrayList<>();
        List<String> knowModules = new ArrayList<>();
        //module_name:""
        // post_count:""
        List<HashMap<String,String>> module_names = moduleMapper.getHotModules(know_hot_modules);

        module_names.forEach((stringStringHashMap -> {
            String module_name = stringStringHashMap.get("module_name");
            knowModules.add(module_name);
        }));

        if (knowModules.stream()
                .findFirst()
                .orElse("life")
                .equals("life"))
        {
            knowModules.forEach(s -> {
                List<Post> posts;
                HotModule hm = new HotModule();
                posts = this.getHots(s,hot_posts);
                hm.setModule_name(s);
                hm.setPosts(posts);
                hotModules.add(hm);
            });
        }
        else {
            List<Post> lifePosts;
            HotModule lifeModule = new HotModule();
            lifePosts = this.getHots("life",hot_posts);
            lifeModule.setModule_name("life");
            lifeModule.setPosts(lifePosts);
            hotModules.add(lifeModule);
            knowModules.forEach(s -> {
                List<Post> posts;
                HotModule hm = new HotModule();
                posts = this.getHots(s,hot_posts);
                hm.setModule_name(s);
                hm.setPosts(posts);
                hotModules.add(hm);
            });
        }
        return hotModules;

    }


}
