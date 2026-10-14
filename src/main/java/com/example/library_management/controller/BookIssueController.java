package com.example.library_management.controller;

import com.example.library_management.entity.BookIssue;
import com.example.library_management.repository.BookIssueRepository;
import com.example.library_management.service.BookIssueService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/book-issue")
public class BookIssueController {

    private final BookIssueService bookIssueService;
    private final BookIssueRepository bookIssueRepository;

    public BookIssueController(
            BookIssueService bookIssueService,
            BookIssueRepository bookIssueRepository){

        this.bookIssueService=bookIssueService;
        this.bookIssueRepository=bookIssueRepository;
    }

    @GetMapping("/student/{studentId}")
    public List<BookIssue> getBooksByStudentId(@PathVariable Long studentId){
        return bookIssueService.getBooksByStudentId(studentId);
    }
    @GetMapping("/student/{studentId}/unreturned")
    public List<BookIssue>getUnreturnedBooks(
            @PathVariable Long studentId){
        return bookIssueService.getUnreturnedBooks(studentId);
    }


    @PostMapping
    public BookIssue addBookIssue(@RequestBody BookIssue bookIssue){
        return bookIssueRepository.save(bookIssue);
    }

    @PutMapping("/{id}/return")
    public String returnBook(@PathVariable Long id){
        BookIssue bookIssue=bookIssueRepository.findById(id).orElseThrow(()->
                new RuntimeException("Book issue not found"));

        bookIssue.setReturned(true);
        bookIssue.setReturnDate(LocalDate.now());

        bookIssueRepository.save(bookIssue);
        return "Book returned successfully";
    }
}
