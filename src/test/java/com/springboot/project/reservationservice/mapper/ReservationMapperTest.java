package com.springboot.project.reservationservice.mapper;

import com.springboot.project.reservationservice.entity.BookingStatus;
import com.springboot.project.reservationservice.entity.Reservation;
import com.springboot.project.reservationservice.model.ReservationDetails;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class ReservationMapperTest {

    @InjectMocks
    ReservationMapper reservationMapper;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testConvertModelToEntity(){
        Reservation reservation = Reservation.builder().reservationId("12c9f1e4a1a04878860b588f42d37a6d").roomId(10)
                .hotelId("12c9f1e4a1a04878860b588f42d37a6d").guestId("12c9f1e4a1a04878860b588f42d37a6d").bookingStatus(BookingStatus.BOOKED).build();
        ReservationDetails reservationDetails = ReservationDetails.builder().reservationId("12c9f1e4a1a04878860b588f42d37a6d").roomId(10)
                .hotelId("12c9f1e4a1a04878860b588f42d37a6d").guestId("12c9f1e4a1a04878860b588f42d37a6d").bookingStatus(BookingStatus.BOOKED.toString()).build();
        Reservation entity = reservationMapper.convertModelToEntity(reservationDetails);
        Assert.assertEquals(entity.getReservationId(),reservation.getReservationId());
    }

    @Test
    public void testConvertEntityToModel(){
        Reservation reservation = Reservation.builder().reservationId("12c9f1e4a1a04878860b588f42d37a6d").roomId(10)
                .hotelId("12c9f1e4a1a04878860b588f42d37a6d").guestId("12c9f1e4a1a04878860b588f42d37a6d").bookingStatus(BookingStatus.BOOKED).build();
        ReservationDetails reservationDetails = ReservationDetails.builder().reservationId("12c9f1e4a1a04878860b588f42d37a6d").roomId(10)
                .hotelId("12c9f1e4a1a04878860b588f42d37a6d").guestId("12c9f1e4a1a04878860b588f42d37a6d").bookingStatus(BookingStatus.BOOKED.toString()).build();
        ReservationDetails details = reservationMapper.convertEntityToModel(reservation);
        Assert.assertEquals(details.getReservationId(),reservationDetails.getReservationId());
    }

    @Test
    public void testConvertEntityToModels(){
        Reservation reservation = Reservation.builder().reservationId("12c9f1e4a1a04878860b588f42d37a6d").roomId(10)
                .hotelId("12c9f1e4a1a04878860b588f42d37a6d").guestId("12c9f1e4a1a04878860b588f42d37a6d").bookingStatus(BookingStatus.BOOKED).build();
        ReservationDetails reservationDetails = ReservationDetails.builder().reservationId("12c9f1e4a1a04878860b588f42d37a6d").roomId(10)
                .hotelId("12c9f1e4a1a04878860b588f42d37a6d").guestId("12c9f1e4a1a04878860b588f42d37a6d").bookingStatus(BookingStatus.BOOKED.toString()).build();
        List<ReservationDetails> details = reservationMapper.convertEntityToModels(Arrays.asList(reservation));
        Assert.assertEquals(details.get(0).getReservationId(),reservationDetails.getReservationId());
    }


}
