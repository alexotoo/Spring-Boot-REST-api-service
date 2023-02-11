package com.alexotoodev.resfulapiservice.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.alexotoodev.resfulapiservice.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
    private UserDaoService service;

    private UserRepository repository;

    public UserJpaResource(UserDaoService service, UserRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    // Get /users
    @GetMapping("/jpa/users")
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    // Get /users
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> getUserByID(@PathVariable int id) {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty())
            throw new UserNotFoundException("id:" + id);

        EntityModel<User> entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).getAllUsers());
        entityModel.add(link.withRel("all-isers"));

        return entityModel;
    }

    // Delete /user
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUserByID(@PathVariable int id) {
        repository.deleteById(id);
    }

    // Post /users
    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = repository.save(user);

        // returns the location of the created user.
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser
                        .getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

}
