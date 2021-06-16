package com.springboot.project.reservationservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.project.reservationservice.entity.BookingStatus;
import com.springboot.project.reservationservice.entity.Reservation;
import com.springboot.project.reservationservice.service.ReservationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class ReservationControllerTest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    ReservationService reservationService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    public void testCreateReservation() throws Exception {
        Reservation reservation = Reservation.builder().reservationId("12c9f1e4a1a04878860b588f42d37a6d").roomId(10)
                .hotelId("12c9f1e4a1a04878860b588f42d37a6d").guestId("12c9f1e4a1a04878860b588f42d37a6d").bookingStatus(BookingStatus.BOOKED).build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/reservations")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(reservation))).andReturn();

        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(201, status);
    }

    @Test
    public void testGetReservedRooms() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/reservations/reservedRooms")
        .contentType(MediaType.APPLICATION_JSON_VALUE).param("fromDate","2020-12-12")
                .param("toDate","2020-12-12")).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void testGetAvailableRoomForReservation() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/reservations/availableReservation")
                .contentType(MediaType.APPLICATION_JSON_VALUE).param("roomId","10")
                        .param("fromDate","2020-12-12")
                .param("toDate","2020-12-12")).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void testGetReservationSummary() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/reservations/12c9f1e4a1a04878860b588f42d37a6d/reservationSummary")
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

}

