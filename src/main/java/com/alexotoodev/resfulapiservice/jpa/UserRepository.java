package com.alexotoodev.resfulapiservice.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexotoodev.resfulapiservice.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
