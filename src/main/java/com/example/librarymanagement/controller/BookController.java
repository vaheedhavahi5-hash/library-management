package com.example.librarymanagement.controller;
import com.example.librarymanagement.entity.Book;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")


public class BookController {
    private final BookService bookService;
    private final BookRepository bookRepository;

    public BookController(BookService bookService,BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository=bookRepository;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookService.createBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Long id,
            @RequestBody Book book) {

        Book updatedBook = bookService.updateBook(id, book);

        if (updatedBook == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    @PatchMapping("/{id}/availability")
    public ResponseEntity<Book> updateAvailability(
            @PathVariable Long id,
            @RequestParam Boolean available) {

        Book book = bookService.updateAvailability(id, available);

        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(book);
    }
    @GetMapping("/search")
    public List<Book> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Boolean available) {

        if (title != null) {
            return bookRepository.findByTitleContainingIgnoreCase(title);
        }

        if (author != null) {
            return bookRepository.findByAuthorContainingIgnoreCase(author);
        }

        if (category != null) {
            return bookRepository.findByCategoryContainingIgnoreCase(category);
        }

        if (available != null) {
            return bookRepository.findByAvailable(available);
        }

        return bookRepository.findAll();



    }}
