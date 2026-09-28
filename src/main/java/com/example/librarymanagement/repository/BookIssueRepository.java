package com.example.librarymanagement.repository;
import com.example.librarymanagement.entity.BookIssue;
import com.example.librarymanagement.entity.IssueStatus;
import com.example.librarymanagement.entity.IssueStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;

public interface BookIssueRepository extends JpaRepository<BookIssue, Long>{
    List<BookIssue> findByMember_Id(Long memberId);
    List<BookIssue>findByStatus(IssueStatus status);
    List<BookIssue> findByDueDateBeforeAndReturnDateIsNull(LocalDate date);


}
