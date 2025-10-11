package com.HCMUS.PHON.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HCMUS.PHON.backend.model.products;

public interface productRepo extends JpaRepository<products, Long> {
    
}
