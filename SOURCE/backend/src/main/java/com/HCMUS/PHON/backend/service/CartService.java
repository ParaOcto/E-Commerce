package com.HCMUS.PHON.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HCMUS.PHON.backend.model.Cart;
import com.HCMUS.PHON.backend.model.CartItem;
import com.HCMUS.PHON.backend.model.Products;
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

    public Optional<Cart> addItemToCart(Long userId, Long productId, int quantity) {
        Optional<Users> user = userRepo.findById(userId);
        if (user.isEmpty()){
            return Optional.empty();
        }
        Optional<Cart> cartOpt = cartRepo.findByUser(user.get());
        
        Optional<Products> product = productRepo.findById(productId);

        //Check if item is already in the cart
        CartItem existingItem = cartOpt.get().getItems().stream()
                .filter(i -> i.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            existingItem.setTotalPrice(existingItem.getQuantity() * product.get().getPrice());
            cartItemRepo.save(existingItem);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cartOpt.get());
            newItem.setProduct(product.get());
            newItem.setQuantity(quantity);
            newItem.setTotalPrice(quantity * product.get().getPrice());
            cartOpt.get().getItems().add(newItem);
            cartItemRepo.save(newItem);

        }
        // ✅ Recalculate total
        double totalAmount = cartOpt.get().getItems().stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
        cartOpt.get().setTotalAmount(totalAmount);
        
        Cart savedCart = cartRepo.save(cartOpt.get());
        return Optional.of(savedCart);

    }

     
}
