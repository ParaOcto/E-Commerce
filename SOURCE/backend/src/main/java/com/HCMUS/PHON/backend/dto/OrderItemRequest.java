package com.HCMUS.PHON.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemRequest {
    private String phoneNumber;
    private String address;
    private String paymentMethod;
}
