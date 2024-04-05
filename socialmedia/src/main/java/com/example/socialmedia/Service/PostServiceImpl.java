package com.example.socialmedia.Service;

import com.example.socialmedia.Model.Post;
import com.example.socialmedia.Model.User;
import com.example.socialmedia.Repository.PostRepo;
import com.example.socialmedia.Repository.UserRepo;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserService userService;
    @Autowired
    UserRepo userRepo;
    @Override
    public Post createNewPost(Post post, Integer id) throws Exception { //id here is the userid

        User user=userService.findUserById(id);
       Post newPost=new Post();
        newPost.setCaption(post.getCaption());
        newPost.setImage(post.getImage());
       //newPost.setCreated(new LocalDateTime.now());
        newPost.setVideo(post.getVideo());
        newPost.setUser(user);

        return postRepo.save(newPost);

    }

    @Override
    public String deletePost(Integer postid, Integer userid) throws Exception{
        Post post=findPostById(postid);
        User user=userService.findUserById(userid);
        if(post.getUser().getId()!=user.getId()){
            throw new Exception("You cant delete another users post");
        }

        postRepo.delete(post);
        return "Post deleted succesfully";
    }

    @Override
    public List<Post> findPostByUserId(Integer userid) {
        return postRepo.findPostByUserId(userid);
    }

    @Override
    public Post findPostById(Integer postid) throws Exception {
        Optional<Post> post= postRepo.findById(postid);
        if (post.isEmpty()) {
            throw new Exception("Post not found with id"+postid);
        }

        return post.get();
    }

    @Override
    public List<Post> findAllPost() {
        return postRepo.findAll();
    }

    @Override
    public Post savedPost(Integer postid, Integer userid) throws Exception{

        Post post=findPostById(postid);
        User user=userService.findUserById(userid);
        if (user.getSavedPost().contains(post)) {
            user.getSavedPost().remove(post);
        }
        else {
            user.getSavedPost().add(post);

        }
         userRepo.save(user);
        return post;

    }

    @Override
    public Post likedPost(Integer postid, Integer userid) throws Exception{

        Post post=findPostById(postid);
        User user=userService.findUserById(userid);
        if (post.getLiked().contains(user)){
            post.getLiked().remove(user);
        }
        else {
            post.getLiked().add(user);
        }

        return postRepo.save(post);
    }
}
