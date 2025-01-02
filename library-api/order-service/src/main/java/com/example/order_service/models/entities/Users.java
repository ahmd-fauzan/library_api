package com.example.order_service.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    
    private Long id;
    private String username;
    private String address;
    private String email;
}
