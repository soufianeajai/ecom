package com.codewithmosh.store.controllers;

import org.springframework.web.bind.annotation.*;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping()
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

}
