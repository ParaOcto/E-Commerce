package com.HCMUS.PHON.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HCMUS.PHON.backend.model.products;
import com.HCMUS.PHON.backend.repository.productRepo;

@Service
public class productService {
    @Autowired
    private productRepo productRepo;

    public List<products> getAllProducts(){
        return productRepo.findAll();
    }

    public products createProduct(products product){
        return productRepo.save(product);
    }
    public products getProductById(Long id){
        return productRepo.findById(id).orElse(null);
    }
    public void deleteProduct(Long id){
        productRepo.deleteById(id);
    }
    public products findProductsByName(String name){
        return productRepo.findByName(name);
    }
    
}
