package com.springboot.project.reservationservice.service.feign;

import com.springboot.project.reservationservice.model.HotelDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="${hotel.service.name}", url="${hotel.service.url}")
public interface HotelServiceFeignClient {

    @GetMapping(value = "${hotel.service.getHotel.path}", consumes = MediaType.APPLICATION_JSON_VALUE)
    HotelDetails getHotel(@PathVariable("hotelId") String hotelId);

}
