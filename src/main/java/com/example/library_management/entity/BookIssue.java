package com.example.library_management.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class BookIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="STUDENT_ID")
    private Long studentId;

    @Column(name="BOOK_ID")
    private Long bookId;

    @Column(name="ISSUE_DATE")
    private LocalDate issueDate;

    @Column(name="RETURN_DATE")
    private LocalDate returnDate;

    @Column(name="RETURNED")
    private boolean returned;

}
