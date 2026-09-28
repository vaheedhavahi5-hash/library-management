package com.example.librarymanagement.service;
import com.example.librarymanagement.entity.IssueStatus;
import com.example.librarymanagement.entity.MemberStatus;
import com.example.librarymanagement.repository.BookIssueRepository;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.repository.MemberRepository;
import com.example.librarymanagement.repository.ReservationRepository;
import com.example.librarymanagement.entity.Book;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service


public class DashboardService {


    private final BookRepository bookRepository;
        private final MemberRepository memberRepository;
        private final BookIssueRepository bookIssueRepository;
        private final ReservationRepository reservationRepository;

        public DashboardService(BookRepository bookRepository,
                                MemberRepository memberRepository,
                                BookIssueRepository bookIssueRepository,
                                ReservationRepository reservationRepository) {
            this.bookRepository = bookRepository;
            this.memberRepository = memberRepository;
            this.bookIssueRepository = bookIssueRepository;
            this.reservationRepository = reservationRepository;
        }

        public Map<String, Object> getDashboard() {

            Map<String, Object> dashboard = new HashMap<>();

            long totalBooks = bookRepository.count();

            long availableBooks = bookRepository.findAll()
                    .stream()
                    .filter(Book::isAvailable)
                    .count();
            long borrowedBooks = bookIssueRepository
                    .findByStatus(IssueStatus.ISSUED)
                    .size();

            long totalMembers = memberRepository.count();

            long activeMembers = memberRepository.findAll()
                    .stream()
                    .filter(member -> member.getStatus() == MemberStatus.ACTIVE)
                    .count();

            long activeReservations = reservationRepository.count();

            long overdueBooks = bookIssueRepository
                    .findByDueDateBeforeAndReturnDateIsNull(LocalDate.now())
                    .size();

            dashboard.put("totalBooks", totalBooks);
            dashboard.put("availableBooks", availableBooks);
            dashboard.put("borrowedBooks", borrowedBooks);
            dashboard.put("totalMembers", totalMembers);
            dashboard.put("activeMembers", activeMembers);
            dashboard.put("activeReservations", activeReservations);
            dashboard.put("overdueBooks", overdueBooks);

            return dashboard;
        }

    }
