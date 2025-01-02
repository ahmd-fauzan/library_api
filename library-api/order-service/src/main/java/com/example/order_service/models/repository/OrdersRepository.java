package com.example.order_service.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.order_service.models.entities.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long>{
    
}
