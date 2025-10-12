package com.HCMUS.PHON.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.HCMUS.PHON.backend.model.products;
import com.HCMUS.PHON.backend.service.cloudinaryService;
import com.HCMUS.PHON.backend.service.productService;

@RestController
@RequestMapping("/api/products")
public class productController {

    @Autowired
    private productService productService;

    @Autowired
    private cloudinaryService cloudinaryService;
    
    @PostMapping("/create")
    public ResponseEntity<products> createProduct(@RequestParam String name,
            @RequestParam String description,
            @RequestParam List<MultipartFile> files,
            @RequestParam double price,
            @RequestParam int quantity,
            @RequestParam Long category_id,
            @RequestParam Long brand_id) {


        List<String> urlImage = new ArrayList<>();
        for (MultipartFile file: files){
            String url = cloudinaryService.uploadFile(file);
            urlImage.add(url);
        }        
        products newProduct = new products();
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setImages(urlImage);
        newProduct.setPrice(price);
        newProduct.setQuantity(quantity);
        newProduct.setCategory_id(category_id);
        newProduct.setBrand_id(brand_id);

        products createdProduct = productService.createProduct(newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping("/all")
    public ResponseEntity<List<products>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/search")
    public ResponseEntity<List<products>> getProductByName(@RequestParam String keyword) {
        List<products> product = productService.findProductsByName(keyword);
        if (!product.isEmpty()) {
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
