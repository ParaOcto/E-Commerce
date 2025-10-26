package com.HCMUS.PHON.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HCMUS.PHON.backend.model.Cart;
import com.HCMUS.PHON.backend.model.Order;
import com.HCMUS.PHON.backend.model.Users;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    Optional<Cart> findByUser(Users user);
}
