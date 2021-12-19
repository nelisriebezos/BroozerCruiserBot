package com.nelisriebezos.broozercruiserbot.domain.service;

import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Correction;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Person;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Trip;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserDB;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserEnvironment;
import com.nelisriebezos.broozercruiserbot.persistence.util.SqlStatement;

import java.sql.ResultSet;
import java.util.ArrayList;
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

//    public List<Trip> findAllTripsByPersonId(Long id) {
//        try (SqlStatement stmt = new SqlStatement(connection, CruiserEnvironment.getQueryString("trip_person_findby_personid"))) {
//            stmt.set("personid", id);
//            ResultSet rs = stmt.executeQuery();
//
//            List<Trip> tripList = new ArrayList<>();
//
//            if (rs.next()) {
//
//            }
//        }
//    }
}

