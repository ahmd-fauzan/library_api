package com.example.book_service.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    
    @NotEmpty(message = "book name cannot be empty")
    private String name;

    @NotEmpty(message = "book description cannot be empty")
    private String description;

    @NotNull(message = "category id cannot be empty")
    private Long categoryId;
}
