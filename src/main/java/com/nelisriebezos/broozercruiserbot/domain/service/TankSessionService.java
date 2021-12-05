package com.nelisriebezos.broozercruiserbot.domain.service;

import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Correction;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.TankSession;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserDB;

import java.util.List;

public class TankSessionService {
    private CruiserDB cruiserDB;

    public TankSessionService(CruiserDB cruiserDB) {
        this.cruiserDB = cruiserDB;
    }

    public Correction create(TankSession tankSession) {
        return null;
    }

    public Correction update(TankSession tankSession) {
        return null;
    }

    public Correction delete(Long id) {
        return null;
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
