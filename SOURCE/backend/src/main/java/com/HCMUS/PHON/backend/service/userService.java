package com.HCMUS.PHON.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.HCMUS.PHON.backend.model.Users;
import com.HCMUS.PHON.backend.repository.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public Users createUser(Users user){
        user.setPassword_hashed(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Users getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public Users findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    
}
