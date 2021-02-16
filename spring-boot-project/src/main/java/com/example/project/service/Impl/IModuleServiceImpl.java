package com.example.project.service.Impl;

import com.example.project.entity.Module;
import com.example.project.entity.Post;
import com.example.project.entity.User;
import com.example.project.mapper.ModuleMapper;
import com.example.project.mapper.PostMapper;
import com.example.project.mapper.UserMapper;
import com.example.project.service.IModuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IModuleServiceImpl implements IModuleService {
    @Resource
    private ModuleMapper moduleMapper;
    @Resource
    private PostMapper postMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public List<Post> getPosts(String module_name){
        List<Post> posts = moduleMapper.getPosts(module_name);
        return posts;
    }

    @Override
    public List<Module> getModules(){
        List<Module> modules = moduleMapper.getModules();
        for(int i=0;i<modules.size();i++){
            Module m = modules.get(i);
            List<String> admins = moduleMapper.getAdmins(m.getModule_name());
            modules.get(i).setAdmins(admins);
        }
        return modules;
    }

    @Override
    public List<Module> searchModule(String key){
        List<Module> modules = moduleMapper.searchModule(key);
        for(int i=0;i<modules.size();i++){
            Module m = modules.get(i);
            List<String> admins = moduleMapper.getAdmins(m.getModule_name());
            modules.get(i).setAdmins(admins);
        }
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

}
