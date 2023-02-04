package com.alexotoodev.resfulapiservice.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public User getUserByID(@PathVariable int id) {
         User user = service.findOne(id);

         if(user == null){
            throw new UserNotFoundException("id:"+id);
         }
         return user;
        }


    //Delete /user
    @DeleteMapping("/users/{id}")
    public void deleteUserByID(@PathVariable int id) {
      service.deleteById(id);
        }

    

    //Post /users
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = service.save(user);
       
        //returns the location of the created user.
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedUser
        .getId()).toUri();

        return ResponseEntity.created(location).build();



    }

}
