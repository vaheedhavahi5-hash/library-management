package com.example.library_management.dto;


import lombok.Data;

@Data

public class BookDto {
    private Long id;
    private String title;
    private String author;
    private  double price;
    private int published;
    private String category;


}
