package com.example.project.mapper;

import com.example.project.entity.Module;
import com.example.project.entity.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ModuleMapper {
    List<Post> getPosts(@Param(value="module_name")String module_name);

    List<Module> getModules();

    List<String> getAdmins(@Param(value="module_name")String module_name);

    List<Module> searchModule(@Param(value="key")String key);

    List<Integer> getPoints(@Param("username")String username,@Param("module_name")String module_name);

    void insertPoint(@Param("username")String username, @Param("module_name")String module_name);
}
