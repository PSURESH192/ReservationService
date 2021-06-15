package com.springboot.project.reservationservice.service.impl;

import com.springboot.project.reservationservice.entity.BookingStatus;
import com.springboot.project.reservationservice.entity.Reservation;
import com.springboot.project.reservationservice.mapper.ReservationMapper;
import com.springboot.project.reservationservice.model.ReservationDetails;
import com.springboot.project.reservationservice.repository.ReservationRepository;
import com.springboot.project.reservationservice.service.ReservationService;
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
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationMapper reservationMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String createReservation(ReservationDetails reservationDetails)
    {
        reservationDetails.setReservationId(UUID.randomUUID().toString().replace("-", ""));
        Reservation reservation = reservationRepository.save(reservationMapper.convertModelToEntity(reservationDetails));
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
}
