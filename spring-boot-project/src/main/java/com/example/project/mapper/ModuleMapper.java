package com.example.project.mapper;

import com.example.project.entity.Module;
import com.example.project.entity.Post;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface ModuleMapper {
    Module getModule(@Param(value = "module_name")String module_name);

    List<Post> getPosts(@Param(value="module_name")String module_name);

    List<Module> getModules();

    List<String> getAdmins(@Param(value="module_name")String module_name);

    List<Module> searchModule(@Param(value="key")String key);

//    Integer getPoints(@Param(value ="username")String username,
//                  @Param(value ="module_name")String module_name);

    //whether there is a record in module_point table
//    int existPoint(@Param(value = "username")String username,@Param(value = "module_name")String module_name);
//    void insertPoint(@Param(value = "username")String username,
//                     @Param(value = "module_name")String module_name,
//                     @Param(value = "point")int point);

//    void insertManagement(ModuleManagement mm);

//    void updateUserpermission(@Param(value = "username")String username,
//                              @Param(value ="permission_id")int permission_id);

    void setAdmin(@Param(value = "username")String username,
                  @Param(value = "module_name")String module_name);

    void deleteAdmin(@Param(value = "username")String username);

    void editIntro(@Param(value = "module_name")String module_name,
                   @Param(value = "new_intro")String new_intro);

    //module_name:post_count
    List<HashMap<String,String>> gethotmodules(@Param("module_count")int module_count);
    //post_id: like_count/comment_count in 24hrs
    List<HashMap<String,Object>> postlikecount(@Param(value = "module_name")String module_name);
    List<HashMap<String,Object>> postcommentcount(@Param(value = "module_name")String module_name);
    //get reply count in 24hrs of a post
    int commentreplycount(@Param(value = "post_id")int post_id);


}
