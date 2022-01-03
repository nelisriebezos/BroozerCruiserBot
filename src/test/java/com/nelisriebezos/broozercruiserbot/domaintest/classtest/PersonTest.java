package com.nelisriebezos.broozercruiserbot.domaintest.classtest;

import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Person;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Trip;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    Person person = new Person();

    @Test
    public void testGetSetId() {
        person.setId(1L);
        assertEquals(1L, person.getId());
    }

    @Test
    public void testGetSetName() {
        person.setName("test");
        assertEquals("test", person.getName());
    }

    @Test
    public void testGetTripList() {
        List<Trip> tripList = new ArrayList<>();
        assertEquals(tripList, person.getTripList());
    }

    @Test
    public void addTrip() {
        Trip trip = new Trip(1L);
        assertTrue(person.addTrip(trip));
    }

    @Test
    public void addExistingTrip() {
        Trip trip = new Trip(1L);
        person.addTrip(trip);
        assertFalse(person.addTrip(trip));
    }

    @Test
    public void removeTrip() {
        Trip trip = new Trip(1L);
        person.addTrip(trip);
        person.removeTrip(trip);
        assertEquals(0, person.getTripList().size());
    }
}
