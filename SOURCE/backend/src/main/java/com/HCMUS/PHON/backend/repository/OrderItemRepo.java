package com.HCMUS.PHON.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HCMUS.PHON.backend.model.OrderItem;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Long>{

    
} 
