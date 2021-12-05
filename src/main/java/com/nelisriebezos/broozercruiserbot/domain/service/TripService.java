package com.nelisriebezos.broozercruiserbot.domain.service;

import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Correction;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Person;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Trip;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserDB;

import java.util.List;

public class TripService {
    private CruiserDB cruiserDB;

    public TripService(CruiserDB cruiserDB) {
        this.cruiserDB = cruiserDB;
    }

    public Correction create(Trip trip) {
        return null;
    }

    public Correction update(Trip trip) {
        return null;
    }

    public Correction delete(Long id) {
        return null;
    }

    public Trip findById(Long id) {
     return null;
    }

    public Trip findByTankSessionId(Long id) {
        return null;
    }

    public List<Person> findAllPerson(Long id) {
        return null;
    }
}

