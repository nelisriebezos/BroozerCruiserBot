package com.nelisriebezos.broozercruiserbot.data.dto.mapper;

import com.nelisriebezos.broozercruiserbot.data.dto.PersistTankSessionDTO;
import com.nelisriebezos.broozercruiserbot.domain.TankSession;
import com.nelisriebezos.broozercruiserbot.utils.DTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PersistTankSessionDTOMapper implements DTOMapper<PersistTankSessionDTO, TankSession> {
    private final TankSessionTripConnector connector;
    private final PersistCarDTOMapper carDTOMapper;

    @Override
    public PersistTankSessionDTO toDTO(TankSession o) {
/*        return PersistTankSessionDTO.builder()
                .id(o.getId())
                .date(o.getDate())
                .tripList(connector.toMasterList(o.getTripList()))
                .car(carDTOMapper.toDTO(o.getCar()))
                .build();*/
        return connector.toMasterDTO(o.getTripList());
    }

    @Override
    public TankSession fromDTO(PersistTankSessionDTO o) {
        return TankSession.builder()
                .id(o.getId())
                .date(o.getDate())
                .tripList(tripDTOMapper.fromMultipleDTO(o.getTripList()))
                .car(carDTOMapper.fromDTO(o.getCar()))
                .build();
    }
}
