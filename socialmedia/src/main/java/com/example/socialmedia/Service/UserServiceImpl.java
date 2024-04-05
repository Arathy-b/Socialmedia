package com.example.socialmedia.Service;

import com.example.socialmedia.Model.User;
import com.example.socialmedia.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{


    @Override
    public User registerUser(User user) {

            User newuser=new User();
            newuser.setFirstname(user.getFirstname());
            newuser.setLastname(user.getLastname());
            newuser.setEmail(user.getEmail());
            newuser.setPassword(user.getPassword());
            userRepo.save(user);
            return newuser;
        }


    @Override
    public User findUserById(Integer id) throws Exception{
        Optional<User> user= userRepo.findById(id);
        if (user.isPresent()){
            return user.get();
        }
        throw new Exception("User not exist with userid"+id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Autowired
    private UserRepo userRepo;
    @Override
    public User followUser(Integer id1, Integer id2) throws Exception {
        //user1 follows user2
        User user1=findUserById(id1);
        User user2=findUserById(id2);

        user2.getFollowers().add(user1.getId());
        user1.getFollowing().add(user2.getId());
        userRepo.save(user1);
        userRepo.save(user2);
        return user1;
    }

    @Override
    public User updateUser(User user,Integer id) throws Exception {
        Optional<User> user1=userRepo.findById(id);
        if (user1.isEmpty()){
            throw new Exception("user not exist with id"+id);
        }
        User oldUser=user1.get();
        if(user.getFirstname()!=null){
            oldUser.setFirstname(user.getFirstname());
        }
        if (user.getLastname()!=null){
            oldUser.setLastname(user.getLastname());
        }
        if(user.getEmail()!=null){
            oldUser.setEmail(user.getEmail());
        }
        User updatedUser=userRepo.save(oldUser);
        return updatedUser;

    }

    @Override
    public List<User> searchUser(String query) {
        List<User> userList=userRepo.searchUser(query);
        if (userList.isEmpty()){
        throw new RuntimeException();
        }else{
            return userRepo.searchUser(query);
        }



    }
}
