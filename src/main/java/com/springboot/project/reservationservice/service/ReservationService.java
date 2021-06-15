package com.springboot.project.reservationservice.service;

import com.springboot.project.reservationservice.model.ReservationDetails;

import java.util.Date;
import java.util.List;

public interface ReservationService {

   String createReservation(ReservationDetails reservationDetails);

   List<ReservationDetails> getReservedRooms(Date reservationFrom,Date reservationTo);

   ReservationDetails getAvailableRoomForReservation(int roomId,Date reservationFrom,Date reservationTo);

}
