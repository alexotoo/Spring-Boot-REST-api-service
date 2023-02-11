package com.alexotoodev.resfulapiservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexotoodev.resfulapiservice.user.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
