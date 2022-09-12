package com.nelisriebezos.broozercruiserbot.data.dto.mapper;

import com.nelisriebezos.broozercruiserbot.data.dto.PersistTankSessionDTO;
import com.nelisriebezos.broozercruiserbot.data.dto.connector.CarTankSessionConnector;
import com.nelisriebezos.broozercruiserbot.data.dto.connector.TankSessionTripConnector;
import com.nelisriebezos.broozercruiserbot.domain.TankSession;
import com.nelisriebezos.broozercruiserbot.utils.DTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PersistTankSessionDTOMapper implements DTOMapper<PersistTankSessionDTO, TankSession> {
    private final TankSessionTripConnector tankSessionTripConnector;
    private final CarTankSessionConnector carTankSessionConnector;
    private final PersistCarDTOMapper carDTOMapper;

    @Override
    public PersistTankSessionDTO toDTO(TankSession o) {
        PersistTankSessionDTO tankSessionDTO = PersistTankSessionDTO.builder()
                .id(o.getId())
                .date(o.getDate())
                .build();

        tankSessionDTO = carTankSessionConnector.addCarDTO(tankSessionDTO, o.getCar());
        return tankSessionTripConnector.addTripDTOList(tankSessionDTO, o.getTripList());
    }

    @Override
    public TankSession fromDTO(PersistTankSessionDTO o) {
        TankSession tankSession = TankSession.builder()
                .id(o.getId())
                .date(o.getDate())
                .car(carDTOMapper.fromDTO(o.getCar()))
                .build();
        tankSession = carTankSessionConnector.addCar(tankSession, o.getCar());
        return tankSessionTripConnector.addTripList(tankSession, o.getTripList());
    }
}
