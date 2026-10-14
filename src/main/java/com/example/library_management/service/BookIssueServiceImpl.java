package com.example.library_management.service;

import com.example.library_management.entity.BookIssue;
import com.example.library_management.repository.BookIssueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookIssueServiceImpl implements BookIssueService {

    private final BookIssueRepository bookIssueRepository;

    public BookIssueServiceImpl(
            BookIssueRepository bookIssueRepository){
        this.bookIssueRepository=bookIssueRepository;
    }

    @Override
    public List<BookIssue>getBooksByStudentId(Long studentId){
        return bookIssueRepository.findAll()
                .stream()
                .filter(bookIssue -> bookIssue.getStudentId().equals(studentId)).toList();
    }
    @Override
    public List<BookIssue>getUnreturnedBooks(Long studentId){
        return bookIssueRepository.findByStudentIdAndReturnedFalse(studentId);
    }


}
