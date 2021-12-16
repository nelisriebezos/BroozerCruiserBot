package com.nelisriebezos.broozercruiserbot.domain.service;

import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Correction;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Person;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Trip;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserDB;

import java.sql.Connection;
import java.util.List;

public class PersonService {
    private final Connection connection;

    public PersonService(Connection connection) {
        this.connection = connection;
    }

    public Person create(Person person) {
        return null;
    }

    public Person update(Person person) {
        return null;
    }

    public void delete(Long id) {
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
