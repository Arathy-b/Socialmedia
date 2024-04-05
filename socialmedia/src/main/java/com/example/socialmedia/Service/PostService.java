package com.example.socialmedia.Service;

import com.example.socialmedia.Model.Post;

import java.util.List;

public interface PostService {
    Post createNewPost(Post post, Integer id) throws Exception;

    String deletePost(Integer postid,Integer userid) throws Exception;
    List<Post> findPostByUserId(Integer userid);
    Post findPostById(Integer postid) throws Exception;
    List<Post> findAllPost();
    Post savedPost(Integer postid,Integer userid) throws Exception;
    Post likedPost(Integer postid,Integer userid) throws Exception;

}
