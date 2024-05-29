package com.nelisriebezos.broozercruiserbot.data.dto.mapper;

import com.nelisriebezos.broozercruiserbot.data.dto.PersistCarDTO;
import com.nelisriebezos.broozercruiserbot.data.dto.connector.CarTankSessionConnector;
import com.nelisriebezos.broozercruiserbot.domain.Car;
import com.nelisriebezos.broozercruiserbot.utils.DTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PersistCarDTOMapper implements DTOMapper<PersistCarDTO, Car> {
    private final CarTankSessionConnector connector;

    @Override
    public PersistCarDTO toDTO(Car o) {
        PersistCarDTO carDTO = PersistCarDTO.builder()
                .id(o.getId())
                .kmCounter(o.getKmCounter())
                .name(o.getName())
                .tankSessionList(tankSessionDTOMapper.toMultipleDTO(o.getTankSessionList()))
                .build();


        return carDTO
    }

    @Override
    public Car fromDTO(PersistCarDTO o) {
        return Car.builder()
                .id(o.getId())
                .kmCounter(o.getKmCounter())
                .name(o.getName())
                .tankSessionList(tankSessionDTOMapper.fromMultipleDTO(o.getTankSessionList()))
                .build();
    }
}
