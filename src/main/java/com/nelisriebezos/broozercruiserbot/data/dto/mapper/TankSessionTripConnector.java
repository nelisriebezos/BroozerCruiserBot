package com.nelisriebezos.broozercruiserbot.data.dto.mapper;

import com.nelisriebezos.broozercruiserbot.data.dto.PersistTankSessionDTO;
import com.nelisriebezos.broozercruiserbot.data.dto.PersistTripDTO;
import com.nelisriebezos.broozercruiserbot.domain.Trip;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TankSessionTripConnector {
    private final PersistTankSessionDTOMapper tankSessionDTOMapper;
    private final PersistTripDTOMapper tripDTOMapper;
    private final PersistCarDTOMapper carDTOMapper;

    public PersistTankSessionDTO toMasterDTO(List<Trip> tripList) {
        List<PersistTripDTO> persistTripDTOS = new ArrayList<>();
        PersistTankSessionDTO tankSessionDTO = null;
        for (Trip trip : tripList) {
            PersistTripDTO dto = PersistTripDTO.builder()
                    .id(trip.getId())
                    .amountOfKm(trip.getAmountOfKm())
                    .date(trip.getDate())
                    .personList(trip.getPersonList())
                    .tankSession(null)
                    .build();
            persistTripDTOS.add(dto);
        }

        for (PersistTripDTO dto : persistTripDTOS) {
            tankSessionDTO = PersistTankSessionDTO.builder()
                    .id(dto.getTankSession().getId())
                    .date(dto.getTankSession().getDate())
                    .tripList(persistTripDTOS)
                    .car(dto.getTankSession().getCar())
                    .build();
            dto.setTankSession(tankSessionDTO);
        }

        return tankSessionDTO;
    };

/*    public List<PersistTankSessionDTO> toSubjectList(TankSession master, PersistTripDTO subject) {

    }*/
}
