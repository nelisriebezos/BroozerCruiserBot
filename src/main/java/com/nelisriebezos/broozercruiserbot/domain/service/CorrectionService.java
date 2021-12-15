package com.nelisriebezos.broozercruiserbot.domain.service;

import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Correction;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserDB;

public class CorrectionService {
    private CruiserDB cruiserDB;

    public CorrectionService(CruiserDB cruiserDB) {
        this.cruiserDB = cruiserDB;
    }

    public Correction create(Correction correction) {
        return null;
    }

    public Correction update(Correction correction) {
        return null;
    }

    public void delete(Long id) {
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
