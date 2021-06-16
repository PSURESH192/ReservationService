package com.springboot.project.reservationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RoomDetails {

    private int roomId;
    private String roomType;
    private int totalRooms;
    private int price;
}
