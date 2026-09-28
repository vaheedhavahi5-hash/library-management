package com.example.librarymanagement.controller;
import com.example.librarymanagement.entity.BookIssue;
import com.example.librarymanagement.entity.IssueRequest;
import com.example.librarymanagement.service.BookIssueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/issues")


public class BookIssueController {
    private final BookIssueService bookIssueService;

    public BookIssueController(BookIssueService bookIssueService) {
        this.bookIssueService = bookIssueService;
    }

    @PostMapping
    public ResponseEntity<BookIssue> issueBook(
            @RequestBody IssueRequest request) {

        BookIssue issue = bookIssueService.issueBook(
                request.getBookId(),
                request.getMemberId()
        );

        return new ResponseEntity<>(issue, HttpStatus.CREATED);

    }
    @PostMapping("/{issueId}/return")
    public ResponseEntity<BookIssue> returnBook(
            @PathVariable Long issueId) {

        BookIssue returnedBook = bookIssueService.returnBook(issueId);

        return ResponseEntity.ok(returnedBook);
    }
    @GetMapping("/active")
    public List<BookIssue> getActiveIssues() {
        return bookIssueService.getActiveIssues();
    }
    @GetMapping("/overdue")
    public List<BookIssue> getOverdueIssues() {
        return bookIssueService.getOverdueIssues();
    }@GetMapping("/{issueId}/fine")
    public double calculateFine(@PathVariable Long issueId) {
        return bookIssueService.calculateFine(issueId);
    }



}
