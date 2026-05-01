package com.investpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.investpro.entity.User;
import com.investpro.service.UserService;

@CrossOrigin(origins = "*")  
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile/{id}")
    public User getProfile(@PathVariable Long id) {
        return userService.getProfile(id);
    }
}
