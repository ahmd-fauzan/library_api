package com.example.order_service.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.order_service.models.entities.Book;
import com.example.order_service.dto.OrdersResponse;
import com.example.order_service.dto.ResponseData;
import com.example.order_service.models.entities.Orders;
import com.example.order_service.models.entities.Users;
import com.example.order_service.services.OrdersService;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public Orders createOrders(@RequestBody Orders orders){
        return ordersService.createOrders(orders);
    }

    @GetMapping
    public Iterable<Orders> getAllOrders(){
        return ordersService.findAll();
    }

    @SuppressWarnings("null")
    @GetMapping("/{id}")
    public OrdersResponse getOrders(@PathVariable Long id){

        Optional<Orders> orders = ordersService.findById(id);
        OrdersResponse response = new OrdersResponse();

        try{
            if(orders.isPresent()){
                ResponseEntity<ResponseData<Book>> dataBook = restTemplate.exchange("http://book-service:8081/api/books/" + orders.get().getBookId(), HttpMethod.GET, null, new ParameterizedTypeReference<ResponseData<Book>>() {});
                ResponseEntity<ResponseData<Users>> dataUsers = restTemplate.exchange("http://user-service:8082/api/users/" + orders.get().getUsersId(), HttpMethod.GET, null, new ParameterizedTypeReference<ResponseData<Users>>() {});
                
                ResponseData<Book> responseBook = dataBook.getBody();
                ResponseData<Users> responseUsers = dataUsers.getBody();

                response.setId(orders.get().getId());
                response.setBook(responseBook.getPayload());
                response.setUser(responseUsers.getPayload());
            }

            return response;
        }catch(Exception e){
            e.printStackTrace();
        }

        return response;
    }
}
