package com.example.user_service.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user_service.models.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{
    
}
