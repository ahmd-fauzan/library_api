package com.example.book_service.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book_service.models.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{
    
}
