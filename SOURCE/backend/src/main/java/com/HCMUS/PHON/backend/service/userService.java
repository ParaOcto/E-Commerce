package com.HCMUS.PHON.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HCMUS.PHON.backend.model.users;
import com.HCMUS.PHON.backend.repository.userRepo;

@Service
public class userService {
    @Autowired
    private userRepo userRepository;

    List<users> getAllUsers(){
        return userRepository.findAll();
    }

    users createUser(users user){
        return userRepository.save(user);
    }

    users getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    users findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
