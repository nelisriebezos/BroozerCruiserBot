package com.nelisriebezos.broozercruiserbot.data.dto.mapper;

import com.nelisriebezos.broozercruiserbot.data.dto.PersistTankSessionDTO;
import com.nelisriebezos.broozercruiserbot.data.dto.PersistTripDTO;
import com.nelisriebezos.broozercruiserbot.domain.TankSession;
import com.nelisriebezos.broozercruiserbot.domain.Trip;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class TankSessionTripConnector {
    private final PersistTankSessionDTOMapper tankSessionDTOMapper;
    private final PersistTripDTOMapper tripDTOMapper;
    private final PersistCarDTOMapper carDTOMapper;

    public PersistTankSessionDTO addTripDTOList(PersistTankSessionDTO tankSessionDTO, List<Trip> tripList) {
        for (Trip trip : tripList) {
            PersistTripDTO dto = PersistTripDTO.builder()
                    .id(trip.getId())
                    .amountOfKm(trip.getAmountOfKm())
                    .date(trip.getDate())
                    .personList(trip.getPersonList())
                    .build();
            tankSessionDTO.addTripDTO(dto);
            dto.setTankSession(tankSessionDTO);
        }
        return tankSessionDTO;
    }

    public TankSession addTripList(TankSession tankSession, List<PersistTripDTO> tripList) {
        for (PersistTripDTO tripDTO : tripList) {
            Trip trip = Trip.builder()
                    .id(tripDTO.getId())
                    .amountOfKm(tripDTO.getAmountOfKm())
                    .date(tripDTO.getDate())
                    .personList(tripDTO.getPersonList())
                    .build();
            tankSession.addTrip(trip);
            trip.setTankSession(tankSessionDTO);
        }
        return tankSessionDTO;
    }

/*    public List<PersistTankSessionDTO> toSubjectList(TankSession master, PersistTripDTO subject) {

    }*/
}
