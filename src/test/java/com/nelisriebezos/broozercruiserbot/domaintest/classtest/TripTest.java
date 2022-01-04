package com.nelisriebezos.broozercruiserbot.domaintest.classtest;

import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Person;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.TankSession;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Trip;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TripTest {
    Trip trip = new Trip();

    @Test
    public void testGetSetId() {
        trip.setId(1L);
        assertEquals(1L, trip.getId());
    }

    @Test
    public void testGetSetMileageInKm() {
        trip.setMileageInKm(100);
        assertEquals(100, trip.getMileageInKm());
    }

    @Test
    public void testGetSetDate() {
        Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        trip.setDate(date);
        assertEquals(date, trip.getDate());
    }

    @Test
    public void testGetSetTankSession() {
        TankSession tankSession = new TankSession();
        trip.setTankSession(tankSession);
        assertEquals(tankSession, trip.getTankSession());
    }

    @Test
    public void testGetPersonList() {
        List<Person> personList = new ArrayList<>();
        assertEquals(personList, trip.getPersonList());
    }

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
