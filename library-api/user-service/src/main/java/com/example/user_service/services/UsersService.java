package com.example.user_service.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user_service.models.entities.Users;
import com.example.user_service.models.repository.UsersRepository;

@Service
public class UsersService {
    
    @Autowired 
    private UsersRepository usersRepository;

    public Users createUser(Users user){
        return usersRepository.save(user);
    }

    public Iterable<Users> findAll(){
        return usersRepository.findAll();
    }

    public Optional<Users> findById(Long id){
        return usersRepository.findById(id);
    }
}
