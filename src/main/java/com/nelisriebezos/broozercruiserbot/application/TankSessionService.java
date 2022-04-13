package com.nelisriebezos.broozercruiserbot.application;

import com.nelisriebezos.broozercruiserbot.data.TankSessionRepository;
import com.nelisriebezos.broozercruiserbot.domain.TankSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@AllArgsConstructor
public class TankSessionService implements ITankSessionService {
    private final TankSessionRepository tankSessionRepository;

    @Override
    public TankSession getTankSession(Long id) {
        return this.tankSessionRepository.getById(id);
    }

    @Override
    public TankSession persistTankSession(TankSession tankSession) {
        return this.tankSessionRepository.save(tankSession);
    }

    @Override
    public boolean deleteTankSession(TankSession tankSession) {
        this.tankSessionRepository.delete(tankSession);
        return true;
    }
}
