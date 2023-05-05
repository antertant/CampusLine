package com.example.project.mapper;

import com.example.project.entity.Module;
import com.example.project.entity.Post;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface ModuleMapper {
    int existModule(@Param(value = "module_name")String module_name);
    void createModuleRequest(@Param(value = "module_name")String module_name);
    void updateModuleRequest(@Param(value = "module_name")String module_name);

    List<HashMap<String,Object>> getRequests();
    void insertModule(@Param(value = "module_name")String module_name);
    void deleteRequest(@Param(value = "module_name")String module_name);

    Module getModule(@Param(value = "module_name")String module_name);

    List<Post> getPosts(@Param(value="module_name")String module_name);

    int getTops(@Param(value = "module_name")String module_name);
    int ifTop(@Param(value = "post_id")int post_id);
    void setTop(@Param(value = "post_id")int post_id);

    List<Module> getModules();

    List<String> getAdmins(@Param(value="module_name")String module_name);

    List<Module> searchModule(@Param(value="key")String key);

    void setAdmin(@Param(value = "username")String username,
                  @Param(value = "module_name")String module_name);

    int deleteAdmin(@Param(value = "username")String username);

    void editIntro(@Param(value = "module_name")String module_name,
                   @Param(value = "new_intro")String new_intro);

    //module_name:post_count
    List<HashMap<String,String>> getHotModules(@Param("module_count")int module_count);
    //post_id: like_count/comment_count in 24hrs
    List<HashMap<String,Object>> postLikeCount(@Param(value = "module_name")String module_name);
    List<HashMap<String,Object>> postCommentCount(@Param(value = "module_name")String module_name);
    //get reply count in 24hrs of a post
    int commentReplyCount(@Param(value = "post_id")int post_id);

    void deleteModule(@Param(value = "moduleName")String moduleName);

}
