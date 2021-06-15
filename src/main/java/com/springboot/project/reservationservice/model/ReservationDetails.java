package com.springboot.project.reservationservice.model;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDetails {

    private String reservationId;
    private String guestId;
    private String hotelId;
    private int roomId;
    private Date reservationFromDate;
    private Date reservationToDate;
    private String bookingStatus;
}
