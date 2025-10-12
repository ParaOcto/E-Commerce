package com.HCMUS.PHON.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class users {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password_hashed;
    private String email;
    private String role;

    // Constructors
    public users() {}
    public users(String username, String password_hashed, String email, String role) {
        this.username = username;
        this.password_hashed = password_hashed;
        this.email = email;
        this.role = role;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_hashed() {
        return password_hashed;
    }
    public void setPassword_hashed(String password_hashed) {
        this.password_hashed = password_hashed;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "users [id=" + id + ", username=" + username + ", password_hashed=" + password_hashed + ", email=" + email
                + ", role=" + role + "]";
    }
}
