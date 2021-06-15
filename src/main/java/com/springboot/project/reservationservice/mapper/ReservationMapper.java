package com.springboot.project.reservationservice.mapper;

import com.googlecode.jmapper.JMapper;
import com.googlecode.jmapper.api.JMapperAPI;
import com.springboot.project.reservationservice.entity.Reservation;
import com.springboot.project.reservationservice.model.ReservationDetails;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class ReservationMapper implements Converter{

    @Override
    public Reservation convertModelToEntity(ReservationDetails reservationDetails) {
        JMapperAPI api = new JMapperAPI()
                .add(JMapperAPI.mappedClass(Reservation.class));
        JMapper<Reservation, ReservationDetails> jMapperEntity = new JMapper<>(Reservation.class, ReservationDetails.class, api);
        return jMapperEntity.getDestination(reservationDetails);
    }

    @Override
    public ReservationDetails convertEntityToModel(Reservation reservation) {
        JMapperAPI api = new JMapperAPI()
                .add(JMapperAPI.mappedClass(ReservationDetails.class));
        JMapper<ReservationDetails, Reservation> jMapperModel = new JMapper<>(ReservationDetails.class, Reservation.class, api);
        return jMapperModel.getDestination(reservation);
    }

    @Override
    public List<ReservationDetails> convertEntityToModels(List<Reservation> reservations) {
        return reservations.stream().filter(Objects::nonNull)
                .map(this::convertEntityToModel)
                .collect(Collectors.toList());

    }

}
