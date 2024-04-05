package com.example.socialmedia.Repository;

import com.example.socialmedia.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    @Query(value = "Select * from post p where p.user_id=:userid ",nativeQuery = true)
    List<Post> findPostByUserId(Integer userid);
}
