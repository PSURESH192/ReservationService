package com.springboot.project.reservationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ReservationExceptionController {
    @ExceptionHandler(value = IDNotFoundException.class)
    public ResponseEntity<Object> exception(IDNotFoundException exception) {
        return new ResponseEntity<>("ID not found", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
