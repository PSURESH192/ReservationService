package com.springboot.project.reservationservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.project.reservationservice.entity.BookingStatus;
import com.springboot.project.reservationservice.entity.Reservation;
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


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReservationServiceApplicationTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	@Test
	void testCreateReservation() {
		Reservation reservation = Reservation.builder().reservationId("12c9f1e4a1a04878860b588f42d37a6d")
				.hotelId("12c9f1e4a1a04878860b588f42d37a6d").guestId("12c9f1e4a1a04878860b588f42d37a6d").bookingStatus(BookingStatus.BOOKED).build();
		HttpEntity<Reservation> entity = new HttpEntity<>(reservation);
		ResponseEntity<String> responseEntity = testRestTemplate.exchange("/reservations", HttpMethod.POST, entity,
				String.class);
		Assertions.assertEquals(responseEntity.getStatusCodeValue(),HttpStatus.CREATED.value());
	}

	@Test
	void testCreateReservationBadRequest() {
		Reservation reservation = Reservation.builder().build();
		HttpEntity<Reservation> entity = new HttpEntity<>(reservation);
		ResponseEntity<String> responseEntity = testRestTemplate.exchange("/reservations", HttpMethod.POST, entity,
				String.class);
		Assertions.assertEquals(responseEntity.getStatusCodeValue(),HttpStatus.INTERNAL_SERVER_ERROR.value());
	}

}
