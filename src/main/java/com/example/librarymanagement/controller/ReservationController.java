package com.example.librarymanagement.controller;
import com.example.librarymanagement.entity.Reservation;
import com.example.librarymanagement.service.ReservationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reservations")


public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation createReservation(
            @RequestParam Long bookId,
            @RequestParam Long memberId) {

        return reservationService.createReservation(bookId, memberId);
    }

    @PutMapping("/{reservationId}/cancel")
    public Reservation cancelReservation(
            @PathVariable Long reservationId) {

        return reservationService.cancelReservation(reservationId);
    }

}
