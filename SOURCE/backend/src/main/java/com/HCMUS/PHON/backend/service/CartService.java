package com.HCMUS.PHON.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HCMUS.PHON.backend.repository.CartRepo;

@Service
public class CartService {
    @Autowired
    private CartRepo cartRepo;
}
