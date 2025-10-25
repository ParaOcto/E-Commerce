package com.HCMUS.PHON.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateQuantityRequest {
    private Long productId;
    private int quantity;
}
