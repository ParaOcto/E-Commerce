package com.HCMUS.PHON.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HCMUS.PHON.backend.model.users;

@Repository
public interface userRepo extends JpaRepository<users, Long> {
    users findByUsername(String username);
} 