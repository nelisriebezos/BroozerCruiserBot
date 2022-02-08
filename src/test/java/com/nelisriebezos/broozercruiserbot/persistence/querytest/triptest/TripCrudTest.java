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
        trip.setMileageInKm(100);
        trip.setDate(date1);
        trip.setTankSession(tankSession);
        Trip createdTrip = tripDAO.create(trip);
        assertEquals(createdTrip, tripDAO.findById(createdTrip.getId()));
    }

    @Test
    public void createNegativeDistance() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            tankSession.setId(1L);
            person.setId(1L);
            trip.addPerson(person);
            trip.setDate(date1);
            trip.setTankSession(tankSession);
            tripDAO.create(trip);
        });
    }

    @Test
    public void createNegativeTimestamp() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            tankSession.setId(1L);
            person.setId(1L);
            trip.addPerson(person);
            trip.setMileageInKm(100);
            trip.setTankSession(tankSession);
            tripDAO.create(trip);
        });
    }

    @Test
    public void createNegativeTanksession() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            person.setId(1L);
            trip.addPerson(person);
            trip.setMileageInKm(100);
            trip.setDate(date1);
            tripDAO.create(trip);
        });
    }

    @Test
    public void createNegativePersonList() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            trip.setMileageInKm(100);
            trip.setDate(date1);
            trip.setTankSession(tankSession);
            tripDAO.create(trip);
        });
    }

    @Test
    public void updatePositive() throws DatabaseException {
        tankSession.setId(1L);
        person.setId(1L);
        trip.addPerson(person);
        trip.setMileageInKm(100);
        trip.setDate(date1);
        trip.setTankSession(tankSession);
        Trip createdTrip = tripDAO.create(trip);
        createdTrip.setDate(date2);
        Trip updatedTrip = tripDAO.update(createdTrip);
        assertEquals(updatedTrip, tripDAO.findById(createdTrip.getId()));
    }

    @Test
    public void updateNegative() {
        Assertions.assertThrows(DatabaseException.class, () ->
                tripDAO.update(trip)
        );
    }

    @Test
    public void deletePositive() throws DatabaseException {
        tankSession.setId(1L);
        person.setId(1L);
        trip.addPerson(person);
        trip.setMileageInKm(100);
        trip.setDate(date1);
        trip.setTankSession(tankSession);
        Trip createdTrip = tripDAO.create(trip);
        tripDAO.delete(createdTrip.getId());

        Assertions.assertThrows(DatabaseException.class, () ->
                    tripDAO.findById(createdTrip.getId())
                );
    }

    @Test
    public void findByIdPositive() throws DatabaseException {
        tankSession.setId(1L);
        person.setId(1L);
        trip.addPerson(person);
        trip.setMileageInKm(100);
        trip.setDate(date1);
        trip.setTankSession(tankSession);
        Trip createdTrip = tripDAO.create(trip);
        assertEquals(createdTrip, tripDAO.findById(createdTrip.getId()));
    }

    @Test
    public void findByIdNegative() {
        Assertions.assertThrows(DatabaseException.class, () ->
                tripDAO.findById(null));
    }

    @Test
    public void findByTankSessionIdPositive() throws DatabaseException {
        List<Trip> tripList = new ArrayList<>();
        tripList.add(tripDAO.findById(1L));
        assertEquals(tripList, tripDAO.findTripsByTankSessionId(1L));
    }

    @Test
    public void findByTankSessionIdNegative() throws DatabaseException {
        assertEquals(0, tripDAO.findTripsByTankSessionId(null).size());
    }

    @Test
    public void findTripsByPersonId() throws DatabaseException {
        List<Trip> tripList = new ArrayList<>();
        Trip testTrip = tripDAO.findById(1l);
        tripList.add(testTrip);
        assertEquals(tripList, tripDAO.findTripsByPersonId(1L));
    }
}
