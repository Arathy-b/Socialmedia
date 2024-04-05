package com.example.socialmedia.Service;

import com.example.socialmedia.Model.User;

import java.util.List;

public interface UserService {
    public User registerUser(User user);
    public User findUserById(Integer id) throws Exception;
    public User findUserByEmail(String email);
    public User followUser(Integer id1, Integer id2) throws Exception;  //we need two id'sone is the id pf user who is folllowing and the next one is the id of which is to be followed
    public User updateUser(User user,Integer id) throws Exception;
    public List<User> searchUser(String query);
}
