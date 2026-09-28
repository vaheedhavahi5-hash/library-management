package com.example.librarymanagement.service;
import com.example.librarymanagement.entity.Book;
import com.example.librarymanagement.entity.BookIssue;
import com.example.librarymanagement.entity.Member;
import com.example.librarymanagement.entity.IssueStatus;
import com.example.librarymanagement.repository.BookIssueRepository;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service


public class BookIssueService {
    private final BookIssueRepository bookIssueRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public BookIssueService(BookIssueRepository bookIssueRepository,
                            BookRepository bookRepository,
                            MemberRepository memberRepository) {
        this.bookIssueRepository = bookIssueRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public BookIssue issueBook(Long bookId, Long memberId) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        if (member.getStatus() != com.example.librarymanagement.entity.MemberStatus.ACTIVE) {
            throw new RuntimeException("Member is not active");
        }

        if (!book.isAvailable()) {
            throw new RuntimeException("Book is not available");
        }

        BookIssue issue = new BookIssue();

        issue.setBook(book);
        issue.setMember(member);
        issue.setIssueDate(LocalDate.now());
        issue.setDueDate(LocalDate.now().plusDays(14));
        issue.setStatus(IssueStatus.ISSUED);

        book.setAvailable(false);
        bookRepository.save(book);

        return bookIssueRepository.save(issue);
    }
    @Transactional
    public BookIssue returnBook(Long issueId) {

        BookIssue issue = bookIssueRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue not found"));

        issue.setReturnDate(LocalDate.now());
        issue.setStatus(IssueStatus.RETURNED);

        Book book = issue.getBook();
        book.setAvailable(true);

        bookRepository.save(book);

        return bookIssueRepository.save(issue);
    }
    public List<BookIssue> getActiveIssues() {
        return
                bookIssueRepository.findByStatus(IssueStatus.ISSUED);
    }
    public List<BookIssue> getOverdueIssues() {
        return bookIssueRepository.findByDueDateBeforeAndReturnDateIsNull(LocalDate.now());
    }
    public double calculateFine(Long issueId) {

        BookIssue issue = bookIssueRepository.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue not found"));

        LocalDate today = LocalDate.now();

        if (issue.getReturnDate() != null) {
            return 0;
        }

        if (!today.isAfter(issue.getDueDate())) {
            return 0;
        }

        long overdueDays = java.time.temporal.ChronoUnit.DAYS.between(
                issue.getDueDate(),
                today
        );

        return overdueDays * 5;
    }

}
