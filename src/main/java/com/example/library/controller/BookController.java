package com.example.library.controller;
import com.example.library.entity.Book;
import com.example.library.service.BookService;
import org.springframework.http.HttpStatus;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
    @GetMapping("/category/{category}")
    public List<Book> getBooksByCategory(@PathVariable String category) {
        return bookService.getBooksByCategory(category);
    }
    @PatchMapping("/{id}/availability")
    public Book updateAvailability(
            @PathVariable Long id,
            @RequestBody Map<String, Boolean> request) {

        Boolean available = request.get("available");

        return bookService.updateAvailability(id, available);
    }
    }




