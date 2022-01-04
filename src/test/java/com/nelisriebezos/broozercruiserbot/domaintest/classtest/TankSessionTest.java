package com.nelisriebezos.broozercruiserbot.domaintest.classtest;

import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Car;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.TankSession;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Trip;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TankSessionTest {
    TankSession tankSession = new TankSession();

    @Test
    public void testGetSetId() {
        tankSession.setId(1L);
        assertEquals(1L, tankSession.getId());
    }

    @Test
    public void testGetSetDate() {
        Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        tankSession.setDate(date);
        assertEquals(date, tankSession.getDate());
    }

    @Test
    public void testGetSetCar() {
        Car car = new Car();
        car.setId(1L);
        tankSession.setCar(car);
        assertEquals(car, tankSession.getCar());
    }

    @Test
    public void testGetTripList() {
        List<Trip> tripList = new ArrayList<>();
        assertEquals(tripList, tankSession.getTripList());
    }

    @Test
    public void addTrip() {
        Trip trip = new Trip();
        trip.setId(1L);
        assertTrue(tankSession.addTrip(trip));
    }

    @Test
    public void addExistingTrip() {
        Trip trip = new Trip();
        trip.setId(1L);
        tankSession.addTrip(trip);
        assertFalse(tankSession.addTrip(trip));
    }

    @Test
    public void removeTrip() {
        Trip trip = new Trip();
        trip.setId(1L);
        tankSession.addTrip(trip);
        tankSession.removeTrip(trip);
        assertEquals(0, tankSession.getTripList().size());
    }
}
