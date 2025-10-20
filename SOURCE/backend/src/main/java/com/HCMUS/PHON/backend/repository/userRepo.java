package com.HCMUS.PHON.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HCMUS.PHON.backend.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
    Users findByUsername(String username);

} 