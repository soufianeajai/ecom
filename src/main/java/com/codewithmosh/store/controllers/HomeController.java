package com.codewithmosh.store.controllers;

import com.codewithmosh.store.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public User index() {
        User user = User.builder().name("Soufiane").build();
        return user;
    }
}
