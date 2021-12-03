package com.nelisriebezos.broozercruiserbot.persistence.service;

import com.nelisriebezos.broozercruiserbot.domain.Person;
import com.nelisriebezos.broozercruiserbot.domain.Trip;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserDB;

import java.util.List;

public class PersonService {
    private CruiserDB cruiserDB;

    public PersonService(CruiserDB cruiserDB) {
        this.cruiserDB = cruiserDB;
    }

    public void create(Person person) {

    }

    public void update(Person person) {

    }

    public void delete(Person person) {

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
