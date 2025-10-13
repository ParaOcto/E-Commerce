package com.HCMUS.PHON.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HCMUS.PHON.backend.model.Products;

@Repository
public interface ProductRepo extends JpaRepository<Products, Long> {
    public List<Products> findByNameContainingIgnoreCase(String keyword);
}
