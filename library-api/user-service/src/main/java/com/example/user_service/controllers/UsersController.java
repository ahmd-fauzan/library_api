package com.example.user_service.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_service.dto.ResponseData;
import com.example.user_service.dto.UsersDto;
import com.example.user_service.models.entities.Users;
import com.example.user_service.services.UsersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    
    @Autowired
    private UsersService usersService;

    @PostMapping
    public ResponseEntity<ResponseData<Users>> createUsers(@Valid @RequestBody UsersDto usersDto, Errors errors){
        
        ResponseData<Users> response = new ResponseData<>();

        if(errors.hasErrors()){
            List<String> messages = new ArrayList<>();

            for (ObjectError error : errors.getAllErrors()) {
                messages.add(error.getDefaultMessage());
            }

            response.setStatus(false);
            response.setMessage(String.join(", ", messages));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.setStatus(true);
        response.setMessage("create users successfully");

        Users user = new Users();
        user.setUsername(usersDto.getUsername());
        user.setAddress(usersDto.getAddress());
        user.setEmail(usersDto.getEmail());

        response.setPayload(usersService.createUser(user));

        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseData<Iterable<Users>>> getAllUsers(){
        ResponseData<Iterable<Users>> response = new ResponseData<>();

        response.setStatus(true);
        response.setMessage("get users successfully");
        response.setPayload(usersService.findAll());

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Users>> getUsersById(@PathVariable Long id){
        
        ResponseData<Users> response = new ResponseData<>();
        
        Optional<Users> user = usersService.findById(id);

        if(user.isPresent()){
            response.setStatus(true);
            response.setMessage("get user by id successfully");
            response.setPayload(user.get());
        }
        else{
            response.setMessage("user not found");
        }

        return ResponseEntity.ok().body(response);
    }
}
