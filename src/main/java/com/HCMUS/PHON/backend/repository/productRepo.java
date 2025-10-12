package com.HCMUS.PHON.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HCMUS.PHON.backend.model.products;

@Repository
public interface productRepo extends JpaRepository<products, Long> {
    public products findByName(String name);
}
