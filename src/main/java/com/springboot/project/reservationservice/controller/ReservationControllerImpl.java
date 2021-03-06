package com.springboot.project.reservationservice.controller;

import com.springboot.project.reservationservice.model.ReservationDetails;
import com.springboot.project.reservationservice.model.ReservationSummary;
import com.springboot.project.reservationservice.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

//ReservationController
@Slf4j
@RestController
public class ReservationControllerImpl implements ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Override
    @PostMapping("/reservations")
    public ResponseEntity<String> createReservation(@Valid @NotNull @RequestBody ReservationDetails reservationDetails) {
        reservationService.createReservation(reservationDetails);
        return new ResponseEntity<>(reservationDetails.getReservationId(), HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/reservations/reservedRooms")
    public ResponseEntity<List<ReservationDetails>> getReservedRooms(@RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date reservationFrom,
                                                              @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date reservationTo) {
        return new ResponseEntity<>(reservationService.getReservedRooms(reservationFrom, reservationTo), HttpStatus.OK);
    }

    @Override
    @GetMapping("/reservations/availableReservation")
    public ResponseEntity<ReservationDetails> getAvailableRoomForReservation(@RequestParam("roomId") int roomId,
                                                           @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date reservationFrom,
                                                           @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date reservationTo) {
        return new ResponseEntity<>(reservationService.getAvailableRoomForReservation(roomId, reservationFrom, reservationTo), HttpStatus.OK);
    }

    @Override
    @GetMapping("/reservations/{reservationId}/reservationSummary")
    public ResponseEntity<ReservationSummary> getReservationSummary(@NotNull @NotBlank @PathVariable("reservationId") String reservationId){
        return new ResponseEntity<>(reservationService.getReservationSummary(reservationId), HttpStatus.OK);
    }
}