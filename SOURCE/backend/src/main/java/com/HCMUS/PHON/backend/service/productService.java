package com.HCMUS.PHON.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HCMUS.PHON.backend.model.Products;
import com.HCMUS.PHON.backend.repository.ProductRepo;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;

    public List<Products> getAllProducts(){
        return productRepo.findAll();
    }

    public Products createProduct(Products product){
        return productRepo.save(product);
    }
    public Products getProductById(Long id){
        return productRepo.findById(id).orElse(null);
    }
    public void deleteProduct(Long id){
        productRepo.deleteById(id);
    }
    public List<Products> findProductsByName(String keyword){
        return productRepo.findByNameContainingIgnoreCase(keyword);
    }
    
    public Products updateProduct(Products updatedProduct){
        Long id = updatedProduct.getId();
        Products existingProduct = productRepo.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setImages(updatedProduct.getImages());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setQuantity(updatedProduct.getQuantity());
            existingProduct.setCategory(updatedProduct.getCategory());
            existingProduct.setBrand(updatedProduct.getBrand());
            return productRepo.save(existingProduct);
        } else {
            return null; // Or throw an exception if preferred
        }
    }
}
