package com.example.project.service.Impl;

import com.example.project.entity.Module;
import com.example.project.entity.Post;
import com.example.project.mapper.ModuleMapper;
import com.example.project.service.IModuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IModuleServiceImpl implements IModuleService {
    @Resource
    private ModuleMapper moduleMapper;

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
}
