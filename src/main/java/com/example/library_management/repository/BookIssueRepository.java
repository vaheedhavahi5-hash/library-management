package com.example.library_management.repository;

import com.example.library_management.entity.BookIssue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookIssueRepository extends JpaRepository<BookIssue,Long> {
    List<BookIssue>findByStudentIdAndReturnedFalse(Long studentId);

}
