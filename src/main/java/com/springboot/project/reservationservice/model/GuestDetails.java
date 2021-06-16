package com.springboot.project.reservationservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestDetails {

    private String guestId;
    private String guestName;
    private Long contact;

    private List<AddressDetails> addressDetails;
}
