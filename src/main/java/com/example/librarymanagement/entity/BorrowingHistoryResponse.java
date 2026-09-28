package com.example.librarymanagement.entity;
import java.time.LocalDate;

public class BorrowingHistoryResponse {
    private String bookName;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private IssueStatus status;

    public BorrowingHistoryResponse(String bookName,
                                    LocalDate issueDate,
                                    LocalDate dueDate,
                                    LocalDate returnDate,
                                    IssueStatus status) {
        this.bookName = bookName;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.status = status;
    }

    public String getBookName() {
        return bookName;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public IssueStatus getStatus() {
        return status;
    }

}
