package com.HCMUS.PHON.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HCMUS.PHON.backend.dto.OrderItemRequest;
import com.HCMUS.PHON.backend.model.Order;
import com.HCMUS.PHON.backend.model.Users;
import com.HCMUS.PHON.backend.repository.UserRepo;
import com.HCMUS.PHON.backend.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody OrderItemRequest orderItemRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        String name = authentication.getName();

        Users user = userRepo.findByUsername(name);
        if (user == null){
            return null;
        }

        Optional<Order> order = orderService.createOrder(user.getId(), 
                                                orderItemRequest.getPhoneNumber(), 
                                                orderItemRequest.getAddress(), 
                                                orderItemRequest.getPaymentMethod());
        return ResponseEntity.ok(order.get());
    }

    @GetMapping("/my/ordering")
    public ResponseEntity<List<Order>> getMyOrders(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<Order> orders = orderService.getOrdersByUser(username);
        return ResponseEntity.ok(orders);
    }
}
