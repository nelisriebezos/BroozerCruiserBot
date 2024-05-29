package com.nelisriebezos.broozercruiserbot.application;

import com.nelisriebezos.broozercruiserbot.domain.TankSession;

public interface ITankSessionService {
    public TankSession getTankSession(Long id);
    public TankSession persistTankSession(TankSession tankSession);
    public boolean deleteTankSession(TankSession tankSession);
}
