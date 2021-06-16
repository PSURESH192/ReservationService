package com.springboot.project.reservationservice.service.impl;

import com.springboot.project.reservationservice.entity.BookingStatus;
import com.springboot.project.reservationservice.entity.Reservation;
import com.springboot.project.reservationservice.mapper.ReservationMapper;
import com.springboot.project.reservationservice.model.GuestDetails;
import com.springboot.project.reservationservice.model.HotelDetails;
import com.springboot.project.reservationservice.model.ReservationDetails;
import com.springboot.project.reservationservice.model.ReservationSummary;
import com.springboot.project.reservationservice.repository.ReservationRepository;
import com.springboot.project.reservationservice.service.ReservationService;
import com.springboot.project.reservationservice.service.feign.GuestServiceFeignClient;
import com.springboot.project.reservationservice.service.feign.HotelServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ReservationMapper reservationMapper;

    @Autowired
    HotelServiceFeignClient hotelServiceFeignClient;

    @Autowired
    GuestServiceFeignClient guestServiceFeignClient;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String createReservation(ReservationDetails reservationDetails)
    {
        reservationDetails.setReservationId(UUID.randomUUID().toString().replace("-", ""));
        reservationRepository.save(reservationMapper.convertModelToEntity(reservationDetails));
        return reservationDetails.getReservationId();
    }

    @Override
    public List<ReservationDetails> getReservedRooms(Date fromDate, Date toDate) {
        List<Reservation> reservations = reservationRepository.
                findByReservationFromDateAndReservationToDate(fromDate, toDate);
        List<Reservation> bookedReservations = reservations.stream().filter(r->r.getBookingStatus().equals(BookingStatus.BOOKED)).collect(Collectors.toList());
        return reservationMapper.convertEntityToModels(bookedReservations);
    }

    @Override
    public ReservationDetails getAvailableRoomForReservation(int roomId,Date reservationFrom,Date reservationTo){
        Optional<Reservation> optionalReservationEntity = reservationRepository.findByRoomIdAndReservationFromDateAndReservationToDate(roomId, reservationFrom, reservationTo);
        return optionalReservationEntity.isPresent() ? reservationMapper.convertEntityToModel(optionalReservationEntity.get()) : null ;
    }

    @Override
    public ReservationSummary getReservationSummary(String reservationId){
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        Reservation reservation = reservationOptional.isPresent() ? reservationOptional.get() : new Reservation();
        HotelDetails hotelDetail = hotelServiceFeignClient.getHotel(reservation.getHotelId());
        GuestDetails guestDetails = guestServiceFeignClient.getGuest(reservation.getGuestId());
        return ReservationSummary.builder().reservationId(reservation.getReservationId())
                .reservationFromDate(reservation.getReservationFromDate()).reservationToDate(reservation.getReservationToDate())
                .bookingStatus(reservation.getBookingStatus().toString())
                .hotelDetails(hotelDetail).guestDetails(guestDetails).build();
    }
}
