package com.nelisriebezos.broozercruiserbot.domaintest.classtest;

import com.nelisriebezos.broozercruiserbot.domain.Person;
import com.nelisriebezos.broozercruiserbot.domain.TankSession;
import com.nelisriebezos.broozercruiserbot.domain.Trip;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TripTest {
    Trip trip = new Trip();

    @Test
    public void addPerson() {
        Person person = new Person();
        person.setId(1L);
        assertTrue(trip.addPerson(person));
    }

    @Test
    public void addExistingPerson() {
        Person person = new Person();
        person.setId(1L);
        trip.addPerson(person);
        assertFalse(trip.addPerson(person));
    }

    @Test
    public void removePerson() {
        Person person = new Person();
        person.setId(1L);
        trip.addPerson(person);
        trip.removePerson(person);
        assertEquals(0, trip.getPersonList().size());
    }
}
