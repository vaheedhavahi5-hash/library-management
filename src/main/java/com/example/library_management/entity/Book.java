package com.example.library_management.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table (name ="BOOKS")
@Data

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name="TITLE")
    private String title;

    @Column(name="AUTHOR")
    private String author;

    @Column(name="PRICE")
    private  double price;

    @Column(name="PUBLISHED_YEAR")
    private int published;

    @Column(name="CATEGORY")
    private String category;

    @Column(name="AVAILABLE")
    private boolean available;






}
