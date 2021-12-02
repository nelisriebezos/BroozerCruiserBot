package com.nelisriebezos.broozercruiserbot.domaintest.classtest;

import com.nelisriebezos.broozercruiserbot.domain.Car;
import com.nelisriebezos.broozercruiserbot.domain.Correctie;
import com.nelisriebezos.broozercruiserbot.domain.TankSession;
import com.nelisriebezos.broozercruiserbot.domain.Trip;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TankSessionTest {
    TankSession tankSession = new TankSession();

    @Test
    public void testGetSetId() {
        tankSession.setId(1L);
        assertEquals(1L, tankSession.getId());
    }

    @Test
    public void testGetSetTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        tankSession.setTimestamp(timestamp);
        assertEquals(timestamp, tankSession.getTimestamp());
    }

    @Test
    public void testGetSetCar() {
        Car car = new Car(1L);
        tankSession.setCar(car);
        assertEquals(car, tankSession.getCar());
    }

    @Test
    public void testGetSetCorrectieList() {
        List<Correctie> correctieList = new ArrayList<>();
        tankSession.setCorrectieList(correctieList);
        assertEquals(correctieList, tankSession.getCorrectieList());
    }

    @Test
    public void addCorrectie() {
        Correctie correctie = new Correctie(1L);
        assertTrue(tankSession.addCorrectie(correctie));
    }

    @Test
    public void addExistingCorrectie() {
        Correctie correctie = new Correctie(1L);
        tankSession.addCorrectie(correctie);
        assertFalse(tankSession.addCorrectie(correctie));
    }

    @Test
    public void removeCorrectie() {
        Correctie correctie = new Correctie(1L);
        tankSession.addCorrectie(correctie);
        tankSession.removeCorrectie(correctie);
        assertEquals(0, tankSession.getCorrectieList().size());
    }

    @Test
    public void testGetSetTripList() {
        List<Trip> tripList = new ArrayList<>();
        tankSession.setTripList(tripList);
        assertEquals(tripList, tankSession.getTripList());
    }

    @Test
    public void addTrip() {
        Trip trip = new Trip(1L);
        assertTrue(tankSession.addTrip(trip));
    }

    @Test
    public void addExistingTrip() {
        Trip trip = new Trip(1L);
        tankSession.addTrip(trip);
        assertFalse(tankSession.addTrip(trip));
    }

    @Test
    public void removeTrip() {
        Trip trip = new Trip(1L);
        tankSession.addTrip(trip);
        tankSession.removeTrip(trip);
        assertEquals(0, tankSession.getTripList().size());
    }
}
