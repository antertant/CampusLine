package com.example.project.service;

import com.example.project.entity.Module;
import com.example.project.entity.Post;

import java.util.List;


public interface IModuleService {
    List<Post> getPosts(String module_name);

    List<Module> getModules();

    List<Module> searchModule(String key);

    int getPoints(String username,String module_name);

    int applym(String username,String module_name);

    void quitAdmin(String username);
}
