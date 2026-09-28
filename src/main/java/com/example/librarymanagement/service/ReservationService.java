package com.example.librarymanagement.service;
import com.example.librarymanagement.entity.Book;
import com.example.librarymanagement.entity.Member;
import com.example.librarymanagement.entity.Reservation;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.repository.MemberRepository;
import com.example.librarymanagement.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service


public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              BookRepository bookRepository,
                              MemberRepository memberRepository) {
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
    }

    public Reservation createReservation(Long bookId, Long memberId) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Reservation reservation = new Reservation();

        reservation.setBook(book);
        reservation.setMember(member);
        reservation.setReservationDate(LocalDate.now());
        reservation.setStatus("ACTIVE");

        return reservationRepository.save(reservation);
    }

    public Reservation cancelReservation(Long reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        reservation.setStatus("CANCELLED");

        return reservationRepository.save(reservation);
    }

}
