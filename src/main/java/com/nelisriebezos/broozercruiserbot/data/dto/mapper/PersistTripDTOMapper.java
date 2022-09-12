package com.nelisriebezos.broozercruiserbot.data.dto.mapper;

import com.nelisriebezos.broozercruiserbot.data.dto.PersistTripDTO;
import com.nelisriebezos.broozercruiserbot.data.dto.connector.TankSessionTripConnector;
import com.nelisriebezos.broozercruiserbot.domain.Trip;
import com.nelisriebezos.broozercruiserbot.utils.DTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PersistTripDTOMapper implements DTOMapper<PersistTripDTO, Trip> {
    private final TankSessionTripConnector connector;

    @Override
    public PersistTripDTO toDTO(Trip o) {
        PersistTripDTO tripDTO = PersistTripDTO.builder()
                .id(o.getId())
                .amountOfKm(o.getAmountOfKm())
                .date(o.getDate())
                .personList(o.getPersonList())
                .build();

        return connector.addTankSessionDTO(tripDTO, o.getTankSession());
    }

    @Override
    public Trip fromDTO(PersistTripDTO o) {
        Trip trip = Trip.builder()
                .id(o.getId())
                .amountOfKm(o.getAmountOfKm())
                .date(o.getDate())
                .personList(o.getPersonList())
                .build();

        return connector.addTankSession(trip, o.getTankSession());
    }
}
