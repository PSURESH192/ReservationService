package com.springboot.project.reservationservice.mapper;

import com.springboot.project.reservationservice.entity.Reservation;
import com.springboot.project.reservationservice.model.ReservationDetails;

import java.util.List;

public interface Converter {

    Reservation convertModelToEntity(ReservationDetails reservationDetails);

    ReservationDetails convertEntityToModel(Reservation reservation);

    List<ReservationDetails> convertEntityToModels(List<Reservation> reservations);
}
