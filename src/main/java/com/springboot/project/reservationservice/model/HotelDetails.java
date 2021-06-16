package com.springboot.project.reservationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HotelDetails {

    private String hotelId;
    private String hotelName;
    private Long contact;
    private String address;
    private String city;
    private Long zipcode;

    private List<RoomDetails> roomDetails;
}
