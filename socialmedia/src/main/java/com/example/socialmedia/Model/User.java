package com.example.socialmedia.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private List<Integer> followers=new ArrayList<>();
    private List<Integer> following=new ArrayList<>();
    @ManyToMany
    private List<Post> savedPost=new ArrayList<>();
    private String gender;


}
