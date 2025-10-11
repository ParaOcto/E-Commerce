package com.HCMUS.PHON.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HCMUS.PHON.backend.model.products;
import com.HCMUS.PHON.backend.service.productService;

@RestController
@RequestMapping("/api/products")
public class productController {

    @Autowired
    private productService productService;
    
    @PostMapping("/create")
    public String createProduct(products pro) {
        productService.createProduct(pro);
        return "Product created successfully";
    }

    @GetMapping("/all")
    public List<products> getAllProducts() {
        return productService.getAllProducts();
    }

}
