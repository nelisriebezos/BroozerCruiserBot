package com.nelisriebezos.broozercruiserbot.persistence.service;

import com.nelisriebezos.broozercruiserbot.domain.Person;
import com.nelisriebezos.broozercruiserbot.domain.Trip;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserDB;

import java.util.List;

public class TripService {
    private CruiserDB cruiserDB;

    public TripService(CruiserDB cruiserDB) {
        this.cruiserDB = cruiserDB;
    }

    public void create(Trip trip) {

    }

    public void update(Trip trip) {

    }

    public void delete(Trip trip) {

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

