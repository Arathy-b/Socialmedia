package com.example.socialmedia.Controller;

import com.example.socialmedia.Model.User;
import com.example.socialmedia.Repository.UserRepo;
import com.example.socialmedia.Service.UserService;
import com.example.socialmedia.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class  UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @PostMapping("/createuser")
    public User createUser(@RequestBody User user){
   User savedUser= userService.registerUser(user);
    return savedUser;
    }
    @GetMapping("/api/getuser")
    public List<User> getUser(){
        return userRepo.findAll();

    }

    @GetMapping("/getuserbyid/{id}")
    public User getUserById(@PathVariable Integer id) throws Exception {
      return userService.findUserById(id);
    }
    @PutMapping("/api/updateuser/{id}")
    public User updateUser(@RequestBody User user,@PathVariable Integer id) throws Exception {

        return userService.updateUser(user,id);

    }

    @DeleteMapping("/deleteuser/{id}")
    public String deleteUser(@PathVariable Integer id){
        userRepo.deleteById(id);
        return "User deleted successfully";
    }
    @PutMapping("/users/follow/{id1}/{id2}")
    public User followUser(@PathVariable Integer id1,@PathVariable Integer id2) throws Exception{
        User user=userService.followUser(id1,id2);
        return user;
    }

    @GetMapping("/searchUser")
    public List<User> searchUser(@RequestParam("query") String query){

        List<User> user=userService.searchUser(query);
        return user;
    }
}
