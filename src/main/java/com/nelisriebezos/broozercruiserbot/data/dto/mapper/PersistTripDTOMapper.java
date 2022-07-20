package com.nelisriebezos.broozercruiserbot.data.dto.mapper;

import com.nelisriebezos.broozercruiserbot.data.dto.PersistTripDTO;
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
        return PersistTripDTO.builder()
                .id(o.getId())
                .amountOfKm(o.getAmountOfKm())
                .date(o.getDate())
                .personList(o.getPersonList())
                .tankSession(connector.)
                .build();
    }

    @Override
    public Trip fromDTO(PersistTripDTO o) {
        return Trip.builder()
                .id(o.getId())
                .amountOfKm(o.getAmountOfKm())
                .date(o.getDate())
                .personList(o.getPersonList())
                .tankSession(tankSessionDTOMapper.fromDTO(o.getTankSession()))
                .build();
    }
}
