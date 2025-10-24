package com.HCMUS.PHON.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddItemRequest {
    private Long productId;
    private int quantity;
}
