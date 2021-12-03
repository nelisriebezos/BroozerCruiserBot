package com.nelisriebezos.broozercruiserbot.persistence.service;

import com.nelisriebezos.broozercruiserbot.domain.TankSession;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserDB;

import java.util.List;

public class TankSessionService {
    private CruiserDB cruiserDB;

    public TankSessionService(CruiserDB cruiserDB) {
        this.cruiserDB = cruiserDB;
    }

    public void create(TankSession tankSession) {

    }

    public void update(TankSession tankSession) {

    }

    public void delete(TankSession tankSession) {

    }

    public TankSession findnById(Long id) {
        return null;
    }

    public List<TankSession> findAll() {
        return null;
    }

    public TankSession findByCarId(Long id) {
        return null;
    }
}
