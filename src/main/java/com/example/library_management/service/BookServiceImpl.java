package com.example.library_management.service;

import com.example.library_management.dto.BookDto;
import com.example.library_management.repository.BookRepository;
import org.springframework.stereotype.Service;
import com.example.library_management.entity.Book;

import java.util.List;
import java.util.ArrayList;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public String addBook(BookDto bookDto) {
        Book book=new Book();

        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPrice(bookDto.getPrice());
        book.setCategory(bookDto.getCategory());
        book.setPublished(bookDto.getPublished());

        Book savedBook=bookRepository.save(book);
        return "Book Created succesfully";
    }

    @Override
    public List<BookDto>getAllBooks(){

        List<Book>books=bookRepository.findAll();

        List<BookDto>bookDtos=new ArrayList<>();

        for (Book book:books){
            BookDto dto=new BookDto();

            dto.setId(book.getId());
            dto.setTitle(book.getTitle());
            dto.setAuthor(book.getAuthor());
            dto.setPrice(book.getPrice());
            dto.setPublished(book.getPublished());
            dto.setCategory(book.getCategory());

            bookDtos.add(dto);
        }

        return bookDtos;
    }
    @Override
    public BookDto getBookById(Long id){
        Book book=bookRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Book not found"));
        BookDto dto=new BookDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPrice(book.getPrice());
        dto.setPublished(book.getPublished());
        dto.setCategory(book.getCategory());
        return dto;

    }
    @Override
    public BookDto updateBook(Long id,BookDto bookDto){
        Book book=bookRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Book not found"));

        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPrice(bookDto.getPrice());
        book.setPublished(bookDto.getPublished());
        book.setCategory(bookDto.getCategory());

        Book updatedBook=bookRepository.save(book);

        BookDto dto=new BookDto();

        dto.setId(updatedBook.getId());
        dto.setTitle(updatedBook.getTitle());
        dto.setAuthor(updatedBook.getAuthor());
        dto.setPrice(updatedBook.getPrice());
        dto.setPublished(updatedBook.getPublished());
        dto.setCategory(updatedBook.getCategory());

        return dto;

    }
    @Override
    public void deleteBook(Long id){
        Book book=bookRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Book not found"));
        bookRepository.delete(book);
    }



}
