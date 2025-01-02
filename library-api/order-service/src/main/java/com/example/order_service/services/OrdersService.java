package com.example.order_service.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order_service.models.entities.Orders;
import com.example.order_service.models.repository.OrdersRepository;

@Service
public class OrdersService {
    
    @Autowired
    private OrdersRepository ordersRepository;

    public Orders createOrders(Orders orders){
        return ordersRepository.save(orders);
    }

    public Iterable<Orders> findAll(){
        return ordersRepository.findAll();
    }

    public Optional<Orders> findById(Long id){
        return ordersRepository.findById(id);
    }
}
