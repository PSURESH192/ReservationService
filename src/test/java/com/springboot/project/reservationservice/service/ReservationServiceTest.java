package com.springboot.project.reservationservice.service;

import com.springboot.project.reservationservice.entity.BookingStatus;
import com.springboot.project.reservationservice.entity.Reservation;
import com.springboot.project.reservationservice.mapper.ReservationMapper;
import com.springboot.project.reservationservice.model.ReservationDetails;
import com.springboot.project.reservationservice.repository.ReservationRepository;
import com.springboot.project.reservationservice.service.impl.ReservationServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
public class ReservationServiceTest {

    @InjectMocks
    ReservationServiceImpl reservationService;

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    ReservationMapper reservationMapper;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateReservation(){
        Reservation reservation = Reservation.builder().reservationId("12c9f1e4a1a04878860b588f42d37a6d").roomId(10)
                .hotelId("12c9f1e4a1a04878860b588f42d37a6d").guestId("12c9f1e4a1a04878860b588f42d37a6d").bookingStatus(BookingStatus.BOOKED).build();
        ReservationDetails reservationDetails = ReservationDetails.builder().reservationId("12c9f1e4a1a04878860b588f42d37a6d").roomId(10)
                .hotelId("12c9f1e4a1a04878860b588f42d37a6d").guestId("12c9f1e4a1a04878860b588f42d37a6d").bookingStatus(BookingStatus.BOOKED.toString()).build();
        Mockito.when(reservationMapper.convertModelToEntity(reservationDetails)).thenReturn(reservation);
        Mockito.when(reservationRepository.save(reservation)).thenReturn(reservation);
        String reservationId = reservationService.createReservation(reservationDetails);
        Assert.assertNotNull(reservationId);
    }

    @Test
    public void testGetReservedRooms(){
        Reservation reservation = Reservation.builder().reservationId("12c9f1e4a1a04878860b588f42d37a6d").roomId(10)
                .hotelId("12c9f1e4a1a04878860b588f42d37a6d").guestId("12c9f1e4a1a04878860b588f42d37a6d")
                .reservationFromDate(new Date()).reservationToDate(new Date()).bookingStatus(BookingStatus.BOOKED).build();
        ReservationDetails reservationDetails = ReservationDetails.builder().reservationId("12c9f1e4a1a04878860b588f42d37a6d").roomId(10)
                .hotelId("12c9f1e4a1a04878860b588f42d37a6d").guestId("12c9f1e4a1a04878860b588f42d37a6d").bookingStatus(BookingStatus.BOOKED.toString()).build();
        Mockito.when(reservationRepository.findByReservationFromDateAndReservationToDate(Mockito.any(),Mockito.any())).thenReturn(Arrays.asList(reservation));
        Mockito.when(reservationMapper.convertEntityToModels(Arrays.asList(reservation))).thenReturn(Arrays.asList(reservationDetails));
        List<ReservationDetails> reservedRooms = reservationService.getReservedRooms(new Date(2020 - 12 - 12), new Date(2020 - 12 - 12));
        Assert.assertNotNull(reservedRooms);
    }

    @Test
    public void testGetAvailableRoomForReservation(){
        Reservation reservation = Reservation.builder().reservationId("12c9f1e4a1a04878860b588f42d37a6d").roomId(10)
                .hotelId("12c9f1e4a1a04878860b588f42d37a6d").guestId("12c9f1e4a1a04878860b588f42d37a6d")
                .reservationFromDate(new Date()).reservationToDate(new Date()).bookingStatus(BookingStatus.AVAILABLE).build();
        ReservationDetails reservationDetails = ReservationDetails.builder().reservationId("12c9f1e4a1a04878860b588f42d37a6d").roomId(10)
                .hotelId("12c9f1e4a1a04878860b588f42d37a6d").guestId("12c9f1e4a1a04878860b588f42d37a6d").bookingStatus(BookingStatus.AVAILABLE.toString()).build();
        Mockito.when(reservationRepository.findByRoomIdAndReservationFromDateAndReservationToDate(10,new Date(2020 - 12 - 12), new Date(2020 - 12 - 12))).thenReturn(Optional.of(reservation));
        Mockito.when(reservationMapper.convertEntityToModel(reservation)).thenReturn(reservationDetails);
        ReservationDetails availableRoomForReservation = reservationService.getAvailableRoomForReservation(10, new Date(2020 - 12 - 12), new Date(2020 - 12 - 12));
        Assert.assertNotNull(availableRoomForReservation);
        Assert.assertEquals(availableRoomForReservation.getRoomId(),reservation.getRoomId());
    }


}
