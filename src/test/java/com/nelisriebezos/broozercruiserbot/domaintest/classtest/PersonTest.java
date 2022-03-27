package com.nelisriebezos.broozercruiserbot.domaintest.classtest;

import com.nelisriebezos.broozercruiserbot.domain.Person;
import com.nelisriebezos.broozercruiserbot.domain.Trip;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    Person person = new Person();

    @Test
    public void addTrip() {
        Trip trip = new Trip();
        trip.setId(1L);
        assertTrue(person.addTrip(trip));
    }

    @Test
    public void addExistingTrip() {
        Trip trip = new Trip();
        trip.setId(1L);
        person.addTrip(trip);
        assertFalse(person.addTrip(trip));
    }

    @Test
    public void removeTrip() {
        Trip trip = new Trip();
        trip.setId(1L);
        person.addTrip(trip);
        person.removeTrip(trip);
        assertEquals(0, person.getTripList().size());
    }
}
