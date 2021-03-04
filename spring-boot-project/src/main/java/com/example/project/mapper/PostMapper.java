package com.example.project.mapper;

import com.example.project.entity.Post;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostMapper {
    void insertPost(@Param(value="post")Post post);

    Post getPost(@Param(value = "post_id")int post_id);

    List<Post> getPostsbyAuthor(@Param(value = "username")String post_author);

    List<Post> searchPosts(@Param(value = "key")String key);

    List<Post> getPosts(@Param(value = "username")String username,
                        @Param(value = "module_name")String module_name);
    int deletePost(@Param(value = "post_id")int post_id);

    //whether the user has liked this post
    int existLike(@Param(value = "post_id")int post_id,@Param(value = "username")String username);
    void insertLike(@Param(value = "post_id")int post_id,@Param(value = "username")String username);
    void deleteLike(@Param(value = "post_id")int post_id,@Param(value = "username")String username);
    List<String> getLikes(@Param(value = "post_id")int post_id);

    //whether the user has collected this post
    int existCollect(@Param(value = "post_id")int post_id,@Param(value = "username")String username);
    void insertCollect(@Param(value = "post_id")int post_id,@Param(value = "username")String username);
    void deleteCollect(@Param(value = "post_id")int post_id,@Param(value = "username")String username);
    List<String> getCollects(@Param(value="username")String username);
    int countCollect(@Param(value="username")String username);

    //update table post
    void updateP(@Param(value = "post_id")int post_id,@Param(value = "choice")int choice);
    //update point at table module_point
//    void updatePoint(@Param(value = "username")String username,
//                     @Param(value = "module_name")String module_name,
//                     @Param(value = "point")int point);


}
