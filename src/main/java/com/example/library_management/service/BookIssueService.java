package com.example.library_management.service;

import com.example.library_management.entity.BookIssue;

import java.util.List;

public interface BookIssueService {
    List<BookIssue>getBooksByStudentId(Long studentId);
    List<BookIssue>getUnreturnedBooks(Long studentId);
}
