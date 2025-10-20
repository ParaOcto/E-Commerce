package com.HCMUS.PHON.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HCMUS.PHON.backend.model.Users;
import com.HCMUS.PHON.backend.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService service;

    @PostMapping("register")
    public Users register(@RequestBody Users user){
        return service.createUser(user);
    }

    @PostMapping("login")
    public String login(@RequestBody Users user){
        return service.verify(user);
    }

}
