package com.springboot.project.reservationservice;

import com.springboot.project.reservationservice.entity.BookingStatus;
import com.springboot.project.reservationservice.entity.Reservation;
import com.springboot.project.reservationservice.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReservationServiceApplicationTests {

	@Autowired
	TestRestTemplate testRestTemplate;

	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testCreateReservation() {
		Reservation reservation = Reservation.builder().reservationId("12c9f1e4a1a04878860b588f42d37a6d")
				.hotelId("12c9f1e4a1a04878860b588f42d37a6d").guestId("12c9f1e4a1a04878860b588f42d37a6d").bookingStatus(BookingStatus.BOOKED).build();
		HttpEntity<Reservation> entity = new HttpEntity<>(reservation);
		ResponseEntity<String> responseEntity = testRestTemplate.exchange("/reservations", HttpMethod.POST, entity,
				String.class);
		Assertions.assertEquals(HttpStatus.CREATED.value(),responseEntity.getStatusCodeValue());
	}

	@Test
	void testCreateReservationBadRequest() {
		Reservation reservation = Reservation.builder().build();
		HttpEntity<Reservation> entity = new HttpEntity<>(reservation);
		ResponseEntity<String> responseEntity = testRestTemplate.exchange("/reservations", HttpMethod.POST, entity,
				String.class);
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(),responseEntity.getStatusCodeValue());
	}

	@Test
	void testGetReservedRooms(){
		ReservationDetails reservation = ReservationDetails.builder().reservationId("12c9f1e4a1a04878860b588f42d37a6d")
				.hotelId("12c9f1e4a1a04878860b588f42d37a6d").guestId("12c9f1e4a1a04878860b588f42d37a6d")
				.reservationFromDate(new Date()).reservationToDate(new Date()).bookingStatus(BookingStatus.BOOKED.toString()).build();
		ResponseEntity<ReservationDetails[]> responseEntity = testRestTemplate
				.getForEntity("/reservations/reservedRooms?fromDate=2020-12-12&toDate=2021-12-31", ReservationDetails[].class);
		Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
	}

	@Test
	void testGetAvailableRoomForReservation(){
		ReservationDetails reservation = ReservationDetails.builder().reservationId("12c9f1e4a1a04878860b588f42d37a6d")
				.hotelId("12c9f1e4a1a04878860b588f42d37a6d").guestId("12c9f1e4a1a04878860b588f42d37a6d")
				.reservationFromDate(new Date()).reservationToDate(new Date()).bookingStatus(BookingStatus.BOOKED.toString()).build();
		ResponseEntity<ReservationDetails> responseEntity = testRestTemplate
				.getForEntity("/reservations/availableReservation?fromDate=2020-12-12&toDate=2021-12-31&roomId=1", ReservationDetails.class);
		Assertions.assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
	}

	@Test
	void testGetReservationSummary(){
		AddressDetails address = AddressDetails.builder().id(1).addressType("HOME").build();
		GuestDetails guest = GuestDetails.builder().guestId("12c9f1e4a1a04878860b588f42d37a6d")
				.guestName("Name").addressDetails(Arrays.asList(address)).build();
		RoomDetails room = RoomDetails.builder().roomId(1).roomType("KING_BED").build();
		HotelDetails hotel = HotelDetails.builder().hotelId("12c9f1e4a1a04878860b588f42d37a6d")
				.hotelName("Name").roomDetails(Arrays.asList(room)).build();
		ReservationSummary reservationSummary = ReservationSummary.builder().reservationId("12c9f1e4a1a04878860b588f42d37a6d")
				.reservationFromDate(new Date()).reservationToDate(new Date()).bookingStatus(BookingStatus.BOOKED.toString())
				.hotelDetails(hotel).guestDetails(guest).build();

		testRestTemplate.execute("/reservations/12c9f1e4a1a04878860b588f42d37a6d/reservationSummary",
				HttpMethod.GET,null, null);
		ResponseEntity<ReservationSummary> responseEntity = testRestTemplate
				.getForEntity("/reservations/12c9f1e4a1a04878860b588f42d37a6d/reservationSummary", ReservationSummary.class);
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,responseEntity.getStatusCode());
	}

}
