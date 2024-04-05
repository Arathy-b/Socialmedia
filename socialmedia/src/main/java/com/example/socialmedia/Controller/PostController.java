package com.example.socialmedia.Controller;

import com.example.socialmedia.Model.Post;
import com.example.socialmedia.Response.APIResponse;
import com.example.socialmedia.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService postService;
    @PostMapping("/createPost/{id}")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable Integer id) throws Exception{
        Post newpost=postService.createNewPost(post,id);
        return new ResponseEntity<>(newpost, HttpStatus.CREATED);

    }
    @GetMapping("/findPostById/{postid}")
    public ResponseEntity<Post> findPostById(@PathVariable Integer postid) throws Exception{
        Post post=postService.findPostById(postid);
        return  new ResponseEntity<>(post,HttpStatus.ACCEPTED);

    }

    @GetMapping("/findPostByUserId/{userid}")
    public ResponseEntity<List<Post>> findPostByUserId(@PathVariable Integer userid) throws Exception{

        List<Post> post=postService.findPostByUserId(userid);
        return new ResponseEntity<List<Post>>(post,HttpStatus.OK);
    }

    @GetMapping("/api/findAllPost")
    public ResponseEntity<List<Post>> findAllPost(){
        return new ResponseEntity<List<Post>>(postService.findAllPost(),HttpStatus.OK);

    }

    @DeleteMapping("/deletePost/{postid}/{userid}")
    public ResponseEntity<String> deletePost(@PathVariable Integer postid, @PathVariable Integer userid) throws Exception{
        String message= postService.deletePost(postid,userid);
        return new ResponseEntity<String>(message,HttpStatus.OK);
    }

    @PutMapping("/savedPost/{postid}/{userid}")
    public ResponseEntity<String> savedPost(@PathVariable Integer postid,@PathVariable Integer userid) throws Exception{
        Post post=postService.savedPost(postid,userid);
        return new ResponseEntity<String>("Post saved succesfully",HttpStatus.ACCEPTED);
    }

    @PutMapping("/likedPost/{postid}/{userid}")
    public ResponseEntity<String> likedPost(@PathVariable Integer postid,@PathVariable Integer userid) throws Exception{
        Post post=postService.likedPost(postid,userid);
        return new ResponseEntity<String>("Post liked succesfully",HttpStatus.ACCEPTED);
    }

}
