package com.example.user_service.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {
    
    @NotEmpty(message = "username cannot be empty")
    private String username;

    @NotEmpty(message = "address cannot be empty")
    private String address;

    @NotEmpty(message = "email cannot be empty")
    private String email;
}
