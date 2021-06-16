package com.springboot.project.reservationservice.controller;

import com.springboot.project.reservationservice.model.ReservationDetails;
import com.springboot.project.reservationservice.model.ReservationSummary;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

public interface ReservationController {

    @ApiOperation(value = "Create Reservation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> createReservation(@Valid @RequestBody ReservationDetails reservationDetails);

    @ApiOperation(value = "Get Reserved Room Details", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ReservationDetails>> getReservedRooms(@RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date reservationFrom,
                                                              @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date reservationTo);

    @ApiOperation(value = "Get Available Room Details", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ReservationDetails> getAvailableRoomForReservation(@RequestParam("roomId") int roomId,
                                                           @RequestParam("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date reservationFrom,
                                                           @RequestParam("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date reservationTo);

    @ApiOperation(value = "Get Reservation Summary", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ReservationSummary> getReservationSummary(@NotNull @NotBlank @PathVariable("reservationId") String reservationId);

}
