package com.springboot.project.reservationservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.googlecode.jmapper.annotations.JMap;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="reservation")
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Reservation {

	@Id
	@Column(name = "reservation_Id")
	@JMap
	private String reservationId;

	@NotBlank(message = "Guest ID is mandatory")
	@Column(name = "guest_Id")
	@JMap
	private String guestId;

	@NotBlank(message = "Hotel ID is mandatory")
	@Column(name = "hotel_Id")
	@JMap
	private String hotelId;

	@Column(name = "room_Id")
	@JMap
	private int roomId;

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "booking_from_date")
	@JMap
	private Date reservationFromDate;

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "booking_end_date")
	@JMap
	private Date reservationToDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "booking_status")
	@JMap
	private BookingStatus bookingStatus;

}