package com.springboot.project.reservationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReservationSummary {

    private String reservationId;
    private Date reservationFromDate;
    private Date reservationToDate;
    private String bookingStatus;

    private HotelDetails hotelDetails;

    private GuestDetails guestDetails;
}
