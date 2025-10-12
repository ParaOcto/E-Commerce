package com.HCMUS.PHON.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<products> createProduct(@RequestBody products pro) {
        products savedProduct = productService.createProduct(pro);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping("/all")
    public ResponseEntity<List<products>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<products> getProductByName(@RequestBody String name) {
        products product = productService.findProductsByName(name);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<products> getProductById(@PathVariable Long id) {
        products product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("Delete successfully");
    }
}
