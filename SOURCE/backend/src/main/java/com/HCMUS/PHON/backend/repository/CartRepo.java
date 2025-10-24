package com.HCMUS.PHON.backend.repository;

import org.springframework.stereotype.Repository;

import com.HCMUS.PHON.backend.model.Cart;
import com.HCMUS.PHON.backend.model.Users;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface CartRepo extends JpaRepository<Cart, Long>{
    Optional<Cart> findByUser(Users user);
}
