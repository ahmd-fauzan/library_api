package com.example.book_service.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book_service.models.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
