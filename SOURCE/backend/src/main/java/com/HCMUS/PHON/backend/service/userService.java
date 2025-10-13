package com.HCMUS.PHON.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HCMUS.PHON.backend.model.Users;
import com.HCMUS.PHON.backend.repository.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;

    List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    Users createUser(Users user){
        return userRepository.save(user);
    }

    Users getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    Users findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    
}
