package com.nelisriebezos.broozercruiserbot.application;

import com.nelisriebezos.broozercruiserbot.data.TankSessionRepository;
import com.nelisriebezos.broozercruiserbot.data.dto.mapper.PersistTankSessionDTOMapper;
import com.nelisriebezos.broozercruiserbot.domain.TankSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@AllArgsConstructor
public class TankSessionService implements ITankSessionService {
    private final TankSessionRepository tankSessionRepository;
    private final PersistTankSessionDTOMapper tankSessionDTOMapper;

    @Override
    public TankSession getTankSession(Long id) {
        return this.tankSessionDTOMapper.fromDTO(this.tankSessionRepository.getById(id));
    }

    @Override
    public TankSession persistTankSession(TankSession tankSession) {
        return this.tankSessionDTOMapper.fromDTO(this.tankSessionRepository.save(this.tankSessionDTOMapper.toDTO(tankSession)));
    }

    @Override
    public boolean deleteTankSession(TankSession tankSession) {
        this.tankSessionRepository.delete(tankSessionDTOMapper.toDTO(tankSession));
        return true;
    }
}
