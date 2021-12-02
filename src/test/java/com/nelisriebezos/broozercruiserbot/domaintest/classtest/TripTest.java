package com.nelisriebezos.broozercruiserbot.domaintest.classtest;

import com.nelisriebezos.broozercruiserbot.domain.Person;
import com.nelisriebezos.broozercruiserbot.domain.TankSession;
import com.nelisriebezos.broozercruiserbot.domain.Trip;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TripTest {
    Trip trip = new Trip();

    @Test
    public void testGetSetId() {
        trip.setId(1L);
        assertEquals(1L, trip.getId());
    }

    @Test
    public void testGetSetDistance() {
        trip.setDistance(100);
        assertEquals(100, trip.getDistance());
    }

    @Test
    public void testGetSetTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        trip.setTimestamp(timestamp);
        assertEquals(timestamp, trip.getTimestamp());
    }

    @Test
    public void testGetSetTankSession() {
        TankSession tankSession = new TankSession();
        trip.setTankSession(tankSession);
        assertEquals(tankSession, trip.getTankSession());
    }

    @Test
    public void testGetSetPersonList() {
        List<Person> personList = new ArrayList<>();
        trip.setPersonList(personList);
        assertEquals(personList, trip.getPersonList());
    }

    @Test
    public void addPerson() {
        Person person = new Person(1L);
        assertTrue(trip.addPerson(person));
    }

    @Test
    public void addExistingPerson() {
        Person person = new Person(1L);
        trip.addPerson(person);
        assertFalse(trip.addPerson(person));
    }

    @Test
    public void removePerson() {
        Person person = new Person(1L);
        trip.addPerson(person);
        trip.removePerson(person);
        assertEquals(0, trip.getPersonList().size());
    }
}
