package com.example.library.service;
import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Book not found with id: " + id
                        )
                );
    }
    public void deleteBook(Long id) {

        if (!bookRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Book not found with id: " + id
            );
        }

        bookRepository.deleteById(id);
    }
    public List<Book> getBooksByCategory(String category) {
        return bookRepository.findByCategory(category);
    }
    public Book updateAvailability(Long id, Boolean available) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Book not found with id: " + id
                        )
                );

        book.setAvailable(available);

        return bookRepository.save(book);
    }


}
