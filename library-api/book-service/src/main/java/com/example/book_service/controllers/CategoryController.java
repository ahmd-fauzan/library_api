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

import com.example.book_service.Dto.CategoryDto;
import com.example.book_service.Dto.ResponseData;
import com.example.book_service.models.entities.Category;
import com.example.book_service.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> createCategory(@Valid @RequestBody CategoryDto categoryDto, Errors errors){

        ResponseData<Category> response = new ResponseData<>();

        if(errors.hasErrors()){
            List<String> messages = new ArrayList<>();

            for (ObjectError error : errors.getAllErrors()) {
                messages.add(error.getDefaultMessage());
            }

            response.setMessage(String.join(", ", messages));

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Category category = new Category();
        category.setName(categoryDto.getName());

        response.setStatus(true);
        response.setPayload(categoryService.createCategory(category));

        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseData<Iterable<Category>>> getAllCategory(){
        return ResponseEntity.ok().body(new ResponseData<>(true, "", categoryService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Category>> getCategory(@PathVariable Long id){
        
        ResponseData<Category> response = new ResponseData<>();

        Optional<Category> data = categoryService.findById(id);

        if(!data.isPresent()){
            response.setMessage("category with id ${id} not found");
        }

        response.setStatus(true);
        response.setPayload(data.get());

        return ResponseEntity.ok().body(response);
    }
}
