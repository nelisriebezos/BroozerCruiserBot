package com.nelisriebezos.broozercruiserbot.persistence.service;

import com.nelisriebezos.broozercruiserbot.domain.Correction;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserDB;

public class CorrectionService {
    private CruiserDB cruiserDB;

    public CorrectionService(CruiserDB cruiserDB) {
        this.cruiserDB = cruiserDB;
    }

    public void create(Correction correction) {

    }

    public void update(Correction correction) {

    }

    public void delete(Correction correction) {

    }

    public Correction findById(Long id) {
        return null;
    }

    public Correction findByTankSessionId(Long id) {
        return null;
    }

    public Correction findByPersonId(Long id) {
        return null;
    }
}
