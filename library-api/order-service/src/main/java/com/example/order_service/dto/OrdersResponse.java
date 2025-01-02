package com.example.order_service.dto;

import com.example.order_service.models.entities.Book;
import com.example.order_service.models.entities.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersResponse {

    private Long id;
    private Book book;
    private Users user;
}