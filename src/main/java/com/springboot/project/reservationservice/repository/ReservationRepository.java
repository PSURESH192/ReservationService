package com.springboot.project.reservationservice.repository;

import com.springboot.project.reservationservice.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {

    List<Reservation> findByReservationFromDateAndReservationToDate(Date fromDate, Date toDate);

    Optional<Reservation> findByRoomIdAndReservationFromDateAndReservationToDate(Integer RoomId, Date fromDate,
                                                                                 Date toDate);
}
