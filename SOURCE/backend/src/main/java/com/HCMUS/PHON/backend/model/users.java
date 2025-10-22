package com.HCMUS.PHON.backend.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
public class Users {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password_hashed;
    private String email;
    private String role;
    private LocalDate created_at = LocalDate.now();

    @OneToOne(mappedBy = "user")
    @JsonIgnore  // Prevent infinite recursion when serializing
    private Cart cart;

    @JsonIgnore
    public String getPassword() {
        return password_hashed;
    }


}
