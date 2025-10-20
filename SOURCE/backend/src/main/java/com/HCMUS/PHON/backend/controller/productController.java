package com.HCMUS.PHON.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.HCMUS.PHON.backend.model.Products;
import com.HCMUS.PHON.backend.service.CloudinaryService;
import com.HCMUS.PHON.backend.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @PutMapping("/update")
    public ResponseEntity<Products> updateProduct(@RequestParam Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam List<MultipartFile> files,
            @RequestParam double price,
            @RequestParam int quantity,
            @RequestParam List<String> category,
            @RequestParam String brand) {

        List<String> urlImage = new ArrayList<>();
        for (MultipartFile file: files){
            String url = cloudinaryService.uploadFile(file);
            urlImage.add(url);
        }        
        Products updatedProduct = new Products();
        updatedProduct.setId(id);
        updatedProduct.setName(name);
        updatedProduct.setDescription(description);
        updatedProduct.setImages(urlImage);
        updatedProduct.setPrice(price);
        updatedProduct.setQuantity(quantity);
        updatedProduct.setCategory(category);
        updatedProduct.setBrand(brand);

        Products product = productService.updateProduct(updatedProduct);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    
    @PostMapping("/create")
    public ResponseEntity<Products> createProduct(@RequestParam String name,
            @RequestParam String description,
            @RequestParam List<MultipartFile> files,
            @RequestParam double price,
            @RequestParam int quantity,
            @RequestParam List<String> category,
            @RequestParam String brand) {


        List<String> urlImage = new ArrayList<>();
        for (MultipartFile file: files){
            String url = cloudinaryService.uploadFile(file);
            urlImage.add(url);
        }        
        Products newProduct = new Products();
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setImages(urlImage);
        newProduct.setPrice(price);
        newProduct.setQuantity(quantity);
        newProduct.setCategory(category);
        newProduct.setBrand(brand);

        Products createdProduct = productService.createProduct(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Products>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Products>> getProductByName(@RequestParam String keyword) {
        List<Products> product = productService.findProductsByName(keyword);
        if (!product.isEmpty()) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id) {
        Products product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id){
        if (productService.getProductById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        else{
            productService.deleteProduct(id);
            return ResponseEntity.ok("Delete successfully");
        }
    }

    
}
