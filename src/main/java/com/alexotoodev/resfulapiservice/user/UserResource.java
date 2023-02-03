package com.alexotoodev.resfulapiservice.user;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource {
    private UserDaoService service;

    public UserResource(UserDaoService service) {
        this.service = service;
    }


    //Get /users
    @GetMapping("/users")
    public List<User> geAlltUsers() {  return  service.findAll(); }   

    //Get /users
    @GetMapping("/users/{id}")
    public User getUserByID(@PathVariable int id) { return service.findOne(id);}

    //Post /users
    @PostMapping("/users")
    public void createUser(@RequestBody User user){
        
        
    }

}
