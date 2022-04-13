package com.nelisriebezos.broozercruiserbot.data.dto.mapper;

import com.nelisriebezos.broozercruiserbot.data.dto.PersistTankSessionDTO;
import com.nelisriebezos.broozercruiserbot.domain.TankSession;
import com.nelisriebezos.broozercruiserbot.utils.DTOMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PersistTankSessionDTOMapper implements DTOMapper<PersistTankSessionDTO, TankSession> {
    private final PersistTripDTOMapper tripDTOMapper;

    @Override
    public PersistTankSessionDTO toDTO(TankSession o) {
        return PersistTankSessionDTO.builder()
                .id(o.getId())
                .date(o.getDate())
                .tripList(tripDTOMapper.toMultipleDTO(o.getTripList()))
                .build();
    }

    @Override
    public TankSession fromDTO(PersistTankSessionDTO o) {
        return TankSession.builder()
                .id(o.getId())
                .date(o.getDate())
                .tripList(tripDTOMapper.fromMultipleDTO(o.getTripList()))
                .build();
    }
}