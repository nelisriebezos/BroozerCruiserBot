package com.nelisriebezos.broozercruiserbot.persistence.querytest.triptest;

import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Trip;
import com.nelisriebezos.broozercruiserbot.persistence.util.DatabaseTest;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TripCrudTest extends DatabaseTest {
    @Test
    public void createPositive() throws DatabaseException {
        person.setId(1L);
        trip.addPerson(person);
        tankSession.setId(1L);
        trip.setDistance(100);
        trip.setDate(date1);
        trip.setTankSession(tankSession);
        Trip createdTrip = tripService.create(trip);
        assertEquals(createdTrip, tripService.findById(createdTrip.getId()));
    }

    @Test
    public void createNegativeDistance() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            tankSession.setId(1L);
            person.setId(1L);
            trip.addPerson(person);
            trip.setDate(date1);
            trip.setTankSession(tankSession);
            tripService.create(trip);
        });
    }

    @Test
    public void createNegativeTimestamp() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            tankSession.setId(1L);
            person.setId(1L);
            trip.addPerson(person);
            trip.setDistance(100);
            trip.setTankSession(tankSession);
            tripService.create(trip);
        });
    }

    @Test
    public void createNegativeTanksession() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            person.setId(1L);
            trip.addPerson(person);
            trip.setDistance(100);
            trip.setDate(date1);
            tripService.create(trip);
        });
    }

    @Test
    public void createNegativePersonList() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            trip.setDistance(100);
            trip.setDate(date1);
            trip.setTankSession(tankSession);
            tripService.create(trip);
        });
    }

    @Test
    public void updatePositive() throws DatabaseException {
        tankSession.setId(1L);
        person.setId(1L);
        trip.addPerson(person);
        trip.setDistance(100);
        trip.setDate(date1);
        trip.setTankSession(tankSession);
        Trip createdTrip = tripService.create(trip);
        createdTrip.setDate(date2);
        Trip updatedTrip = tripService.update(createdTrip);
        assertEquals(updatedTrip, tripService.findById(createdTrip.getId()));
    }

    @Test
    public void updateNegative() {
        Assertions.assertThrows(DatabaseException.class, () ->
                tripService.update(trip)
        );
    }

    @Test
    public void deletePositive() throws DatabaseException {
        tankSession.setId(1L);
        person.setId(1L);
        trip.addPerson(person);
        trip.setDistance(100);
        trip.setDate(date1);
        trip.setTankSession(tankSession);
        Trip createdTrip = tripService.create(trip);
        tripService.delete(createdTrip.getId());

        Assertions.assertThrows(DatabaseException.class, () ->
                    tripService.findById(createdTrip.getId())
                );
    }

    @Test
    public void findByIdPositive() throws DatabaseException {
        tankSession.setId(1L);
        person.setId(1L);
        trip.addPerson(person);
        trip.setDistance(100);
        trip.setDate(date1);
        trip.setTankSession(tankSession);
        Trip createdTrip = tripService.create(trip);
        assertEquals(createdTrip, tripService.findById(createdTrip.getId()));
    }

    @Test
    public void findByIdNegative() {
        Assertions.assertThrows(DatabaseException.class, () ->
                tripService.findById(null));
    }

    @Test
    public void findByTankSessionIdPositive() throws DatabaseException {
        List<Trip> tripList = new ArrayList<>();
        tripList.add(tripService.findById(1L));
        assertEquals(tripList, tripService.findByTankSessionId(1L));
    }

    @Test
    public void findByTankSessionIdNegative() throws DatabaseException {
        assertEquals(0, tripService.findByTankSessionId(null).size());
    }

    @Test
    public void findTripsByPersonId() throws DatabaseException {
        List<Trip> tripList = new ArrayList<>();
        Trip testTrip = tripService.findById(1l);
        tripList.add(testTrip);
        assertEquals(tripList, tripService.findTripsByPersonId(1L));
    }
}
