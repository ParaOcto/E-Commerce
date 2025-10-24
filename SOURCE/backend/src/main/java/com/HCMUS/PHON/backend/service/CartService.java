package com.HCMUS.PHON.backend.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HCMUS.PHON.backend.model.Cart;
import com.HCMUS.PHON.backend.model.Users;
import com.HCMUS.PHON.backend.repository.CartItemRepo;
import com.HCMUS.PHON.backend.repository.CartRepo;
import com.HCMUS.PHON.backend.repository.ProductRepo;
import com.HCMUS.PHON.backend.repository.UserRepo;

@Service
public class CartService {
    @Autowired
    private CartRepo cartRepo;
    @Autowired
    private CartItemRepo cartItemRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ProductRepo productRepo;

    public Optional<Cart> getCartByUserId(Long userId){
        Optional<Users> user = userRepo.findById(userId);

        if (user.isEmpty()){
            return Optional.empty();
        }

        Optional<Cart> cartOpt = cartRepo.findByUser(user.get());

        if (cartOpt.isPresent()) {
            return cartOpt;
        }

        // Create new cart if not found
        Cart newCart = new Cart();
        newCart.setUser(user.get());
        cartRepo.save(newCart);

        return Optional.of(newCart);    
}

    // public Optional<Cart> 

     
}
