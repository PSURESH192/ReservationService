package com.springboot.project.reservationservice.service.feign;

import com.springboot.project.reservationservice.model.GuestDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="${guest.service.name}", url="${guest.service.url}")
public interface GuestServiceFeignClient {

    @GetMapping(value = "${guest.service.getGuest.path}", consumes = MediaType.APPLICATION_JSON_VALUE)
    GuestDetails getGuest(@PathVariable("guestId") String guestId);

}
