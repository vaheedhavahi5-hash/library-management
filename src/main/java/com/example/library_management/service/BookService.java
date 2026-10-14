package com.example.library_management.service;

import com.example.library_management.dto.BookDto;
import java.util.List;

public interface BookService {

    String addBook(BookDto bookDto);
   List<BookDto>getAllBooks();
   BookDto getBookById(Long id);
   BookDto updateBook(Long id,BookDto bookDto);
   void deleteBook(Long id);

}






