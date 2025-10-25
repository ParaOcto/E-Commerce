package com.HCMUS.PHON.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HCMUS.PHON.backend.dto.AddItemRequest;
import com.HCMUS.PHON.backend.dto.DeleteItemRequest;
import com.HCMUS.PHON.backend.dto.UpdateQuantityRequest;
import com.HCMUS.PHON.backend.model.Cart;
import com.HCMUS.PHON.backend.model.Users;
import com.HCMUS.PHON.backend.repository.UserRepo;
import com.HCMUS.PHON.backend.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/item")
    public ResponseEntity<Cart> getUserCart(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String name = authentication.getName();

        Users user =  userRepo.findByUsername(name);
        if (user == null){
            return null;
        }
        Cart cart = cartService.getCartByUserId(user.getId())
            .orElseThrow(() -> new RuntimeException("Cart not found!"));

        return ResponseEntity.ok(cart);
    }

    @PostMapping("/add/item")
    public ResponseEntity<Cart> addItemToCart(@RequestBody AddItemRequest addItemRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String name = authentication.getName();

        Users user =  userRepo.findByUsername(name);
        if (user == null){
            return null;
        }
        Cart cart = cartService.addItemToCart(user.getId(), addItemRequest.getProductId(), addItemRequest.getQuantity())
            .orElseThrow(() -> new RuntimeException("Can't add this item!"));
        
        return ResponseEntity.ok(cart);

    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteItem(@RequestBody DeleteItemRequest deleteItemRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String name = authentication.getName();

        Users user =  userRepo.findByUsername(name);
        if (user == null){
            return null;
        }

        cartService.deleteItem(user.getId(), deleteItemRequest.getProductId());
        return ResponseEntity.ok("Item deleted");
    }

    @PutMapping("/update/quantity")
    public ResponseEntity<String> updateQuantity(@RequestBody UpdateQuantityRequest updateQuantityRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String name = authentication.getName();

        Users user =  userRepo.findByUsername(name);
        if (user == null){
            return null;
        }

        cartService.updateQuantity(user.getId(), updateQuantityRequest.getProductId(), updateQuantityRequest.getQuantity());
        return ResponseEntity.ok("Item updated");
    }
}
