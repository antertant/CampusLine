package com.example.project.service;

import com.example.project.entity.HotModule;
import com.example.project.entity.Module;
import com.example.project.entity.Post;

import java.util.List;


public interface IModuleService {
    int createModule(String module_name);

    List<Post> getPosts(String module_name);

    int toTop(int post_id);

    List<Module> getModules();

    List<Module> searchModule(String key);

    int getPoints(String username,String module_name);

    int applym(String username,String module_name);

    void quitAdmin(String username);

    void editintro(String module_name,String new_intro);

    List<HotModule> gethotmodules();
}
