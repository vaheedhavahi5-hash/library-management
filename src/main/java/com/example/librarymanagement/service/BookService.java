package com.example.librarymanagement.service;
import com.example.librarymanagement.entity.Book;
import com.example.librarymanagement.repository.BookRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> existingBook = bookRepository.findById(id);

        if (existingBook.isPresent()) {
            Book book = existingBook.get();

            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setCategory(updatedBook.getCategory());
            book.setAvailable(updatedBook.isAvailable());

            return bookRepository.save(book);
        }

        return null;
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    public Book updateAvailability(Long id, Boolean available) {
        Optional<Book> existingBook = bookRepository.findById(id);

        if (existingBook.isPresent()) {
            Book book = existingBook.get();
            book.setAvailable(available);
            return bookRepository.save(book);
        }

        return null;
    }
    public List<Book> searchBooks(
            String title,
            String author,
            String category,
            Boolean available) {

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
    }


}
