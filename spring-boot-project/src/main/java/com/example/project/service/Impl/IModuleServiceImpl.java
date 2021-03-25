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
                moduleMapper.createModulerequest(module_name);
            else
                moduleMapper.updateModulerequest(module_name);
            return 0;
        }
        else
            return 1;
    }

    @Override
    public List<Post> getPosts(String module_name){
        List<Post> posts = moduleMapper.getPosts(module_name);
        return posts;
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
            int count = moduleMapper.getTopc(module_name);
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
        List<Module> modules = moduleMapper.getModules();
        return modules;
    }

    @Override
    public List<Module> searchModule(String key){
        List<Module> modules = moduleMapper.searchModule(key);
        return modules;
    }

    @Override
    public int getPoints(String username,String module_name){
        int points = 0;
        List<Post> posts = postMapper.getPosts(username,module_name);
        for(Post p:posts){
            int likes = p.getPost_likes();
            int collects = p.getPost_collections();
            int comments = p.getPost_comments();
            points += likes*2+comments*1+collects*3;
        }
        return points;
    }

    @Override
    public int applym(String username, String module_name){
        int leastpoint = 2;//minimum points to become an admin
        User user = userMapper.selectByPrimaryKey(username);
        int managers = moduleMapper.getAdmins(module_name).size();
        int maxadmins = moduleMapper.getModule(module_name).getMax_adminNumber();
        if(user.getModule_admin()!=null && user.getModule_admin()!=""){
            return 1;//you have been manager of some module
        }
        else if(managers>=maxadmins){
            return 2;//the module has had enough managers
        }

        else if(this.getPoints(username,module_name)<leastpoint){
            return 3;//dont have enough points to apply for a manager
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
    public void editintro(String module_name,String new_intro){
        moduleMapper.editIntro(module_name,new_intro);
    }

    public List<Post> gethots(String module_name,int count){
        List<Post> posts = moduleMapper.getPosts(module_name);
        //"post_id": int
        // "like_count/comment_count"-"count(1)":int
        List<HashMap<String,Object>> likecounts = moduleMapper.postlikecount(module_name);
        List<HashMap<String,Object>> commentcounts = moduleMapper.postcommentcount(module_name);
        HashMap<Integer,Integer> likecount = HashmapUtils.trans(likecounts);
        HashMap<Integer,Integer> commentcount = HashmapUtils.trans(commentcounts);

        //post_id:total_like&comment&reply_counts within 24hrs
        HashMap<Integer,Integer> hashMap = new HashMap();
        for(int i=0;i<posts.size();i++){
            int post_id = posts.get(i).getPost_id();
            int like_count = (likecount.get(post_id)==null)?0:likecount.get(post_id);
            int comment_count = (commentcount.get(post_id)==null)?0:commentcount.get(post_id);
            int reply_count = moduleMapper.commentreplycount(post_id);
            int total_count = like_count+comment_count+reply_count;
            hashMap.put(post_id,total_count);
        }
        //post_id:count
        List<Post> hotposts = new ArrayList<>();
        List<Map.Entry<Integer,Integer>> list = HashmapUtils.sort(hashMap,count);
        for(Map.Entry<Integer,Integer> m:list){
            hotposts.add(postMapper.getPost(m.getKey()));
        }
        return HTMLUtils.tohtmls(hotposts);
    }

    @Override
    public List<HotModule> gethotmodules(){
        int life_hot_posts = 5;
        int know_hot_modules = 4, know_hot_posts = 3;
        List<HotModule> hotModules = new ArrayList();
        List<String> knowmodules = new ArrayList<>();
        //module_name:""
        // post_count:""
        List<HashMap<String,String>> module_names = moduleMapper.gethotmodules(know_hot_modules);
        for(int i=0;i<module_names.size();i++){
            HashMap h = module_names.get(i);
            String module_name = (String) h.get("module_name");
            knowmodules.add(module_name);
        }

        for(int i=0;i<1+knowmodules.size();i++){
            List<Post> posts = new ArrayList<>();
            if(i == 0){
                posts = this.gethots("life",life_hot_posts);
                HotModule hm = new HotModule();
                hm.setModule_name("life");
                hm.setPosts(posts);
                hotModules.add(hm);
            }
            else{
                String module_name = knowmodules.get(i-1);
                posts = this.gethots(module_name,know_hot_posts);
                HotModule hm = new HotModule();
                hm.setModule_name(module_name);
                hm.setPosts(posts);
                hotModules.add(hm);
            }
        }
        return hotModules;

    }


}
