package com.example.socialmedia.Repository;

import com.example.socialmedia.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User,Integer> {

    public User findByEmail(String email);
    @Query(value = "select * from user u where u.firstname LIKE %:query% OR u.lastname LIKE %:query% OR u.email LIKE %:query%",nativeQuery = true)
    public List<User> searchUser(@Param("query") String query);
}
