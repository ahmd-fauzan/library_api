package com.example.book_service.controllers;

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

import com.example.book_service.Dto.BookDto;
import com.example.book_service.Dto.ResponseData;
import com.example.book_service.models.entities.Book;
import com.example.book_service.models.entities.Category;
import com.example.book_service.services.BookService;
import com.example.book_service.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {
    
    @Autowired
    private BookService bookService;

    @Autowired CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ResponseData<Book>> createBook(@Valid @RequestBody BookDto bookDto, Errors errors){

        ResponseData<Book> response = new ResponseData<>();

        if(errors.hasErrors()){
            List<String> messages = new ArrayList<>();

            for (ObjectError error : errors.getAllErrors()) {
                messages.add(error.getDefaultMessage());
            }

            response.setStatus(false);
            response.setMessage(String.join(", ", messages));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Optional<Category> category = categoryService.findById(bookDto.getCategoryId());

        if(!category.isPresent()){
            response.setStatus(false);
            response.setMessage("category not found");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        response.setStatus(true);
        response.setMessage("book successfully added");
        
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setDescription(bookDto.getDescription());
        book.setCategory(category.get());

        response.setPayload(bookService.createBook(book));

        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseData<Iterable<Book>>> getAllBook(){
        return ResponseEntity.ok().body(new ResponseData<>(true, "", bookService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Book>> getBookById(@PathVariable Long id){
        ResponseData<Book> response = new ResponseData<>();

        Optional<Book> data = bookService.findById(id);

        if(!data.isPresent()){
            response.setMessage("book with id ${id} not found");
        }
        
        response.setStatus(true);
        response.setPayload(data.get());

        return ResponseEntity.ok().body(response);
    }
}
