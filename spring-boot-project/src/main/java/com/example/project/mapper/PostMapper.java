package com.example.project.mapper;

import com.example.project.entity.Post;
import com.example.project.entity.PostLike;
import org.apache.ibatis.annotations.Param;
import com.example.project.entity.NewMsg;

import java.util.List;

public interface PostMapper {
    void updatePost(@Param(value="post")Post post);

    void insertPost(@Param(value="post")Post post);

    Post getPost(@Param(value = "post_id")int post_id);

    List<Post> getPostsbyAuthor(@Param(value = "username")String username);
    List<Post> getFollowPost(@Param(value = "follower")String follower);

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
    List<Post> getCollects(@Param(value="username")String username);
    int countCollect(@Param(value="username")String username);

    List<PostLike> getLikedPost(@Param(value = "username")String username);

    //update table post
    void updateP(@Param(value = "post_id")int post_id,@Param(value = "choice")int choice);
    void updateV(@Param(value="username")String username,@Param(value = "choice")int choice);
    int getNew1(@Param(value = "username")String username);
    int getNew2(@Param(value = "username")String username);
    int getNew3(@Param(value = "username")String username);
    int getNew4(@Param(value = "username")String username);
    //update point at table module_point
//    void updatePoint(@Param(value = "username")String username,
//                     @Param(value = "module_name")String module_name,
//                     @Param(value = "point")int point);


}
