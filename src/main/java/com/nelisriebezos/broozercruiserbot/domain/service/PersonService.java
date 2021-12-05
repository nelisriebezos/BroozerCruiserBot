package com.nelisriebezos.broozercruiserbot.domain.service;

import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Correction;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Person;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Trip;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserDB;

import java.util.List;

public class PersonService {
    private CruiserDB cruiserDB;

    public PersonService(CruiserDB cruiserDB) {
        this.cruiserDB = cruiserDB;
    }

    public Correction create(Person person) {
        return null;
    }

    public Correction update(Person person) {
        return null;
    }

    public Correction delete(Long id) {
        return null;
    }

    public Person findById(Long id) {
    return null;
    }

    public Person findByName(Person person) {
        return null;
    }

    public List<Trip> findAllTrips(Long id) {
        return null;
    }
}
