package com.example.book_service.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book_service.models.entities.Book;
import com.example.book_service.models.repository.BookRepository;

@Service
public class BookService {
    
    @Autowired
    private BookRepository bookRepository;

    @Autowired CategoryService categoryService;

    public Book createBook(Book book){
        return bookRepository.save(book);
    }

    public Iterable<Book> findAll(){
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id){
        return bookRepository.findById(id);
    }
}
