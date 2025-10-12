package com.HCMUS.PHON.backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class products {
    //Id automatic generation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String image;
    private int price;
    private int quantity;
    private Long categoryId;
    private Long brandId;
    // Date format: yyyy-MM-dd and automatic generation
    @Column(updatable = false)
    private LocalDateTime createdAt;

  
    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    
}
