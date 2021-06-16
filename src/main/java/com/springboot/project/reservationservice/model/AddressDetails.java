package com.springboot.project.reservationservice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDetails {

    private int id;
    private String addressType;
    private String street;
    private String city;
    private Integer zipcode;
    private String state;

}
