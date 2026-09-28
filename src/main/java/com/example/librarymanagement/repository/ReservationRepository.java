package com.example.librarymanagement.repository;
import com.example.librarymanagement.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
