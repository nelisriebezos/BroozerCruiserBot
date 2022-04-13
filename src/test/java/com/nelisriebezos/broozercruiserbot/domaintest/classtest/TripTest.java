package com.nelisriebezos.broozercruiserbot.domaintest.classtest;

import com.nelisriebezos.broozercruiserbot.domain.Person;
import com.nelisriebezos.broozercruiserbot.domain.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TripTest {
    Trip trip;
    Person person1;
    Person person2;
    Person person3;
    Person person4;

    @BeforeEach
    public void init() {
        trip = new Trip();
        person1 = new Person("person1");
        person1.setId(1L);
        person2 = new Person("person2");
        person2.setId(2L);
        person3 = new Person("person3");
        person3.setId(3L);
        person4 = new Person("person4");
        person4.setId(4L);
    }

    @Test
    @DisplayName("calculateKmPerPerson one person")
    public void calculateKmPerPersonOne() {
        trip.addPerson(person1);
        trip.setAmountOfKm(100);
        HashMap<String, Double> result = trip.calculateKmPerPerson();
        assertEquals(1, result.size());
        assertTrue(result.containsKey("person1"));
        assertEquals(100.0, result.get("person1"));
    }

    @Test
    @DisplayName("calculateKmPerPerson two persons")
    public void calculateKmPerPersonTwo() {
        trip.addPerson(person1);
        trip.addPerson(person2);
        trip.setAmountOfKm(100);
        HashMap<String, Double> result = trip.calculateKmPerPerson();
        assertEquals(2, result.size());
        assertTrue(result.containsKey("person1"));
        assertTrue(result.containsKey("person2"));
        assertEquals(50.0, result.get("person1"));
        assertEquals(50.0, result.get("person2"));
    }

    @Test
    @DisplayName("calculateKmPerPerson three persons")
    public void calculateKmPerPersonThree() {
        trip.addPerson(person1);
        trip.addPerson(person2);
        trip.addPerson(person3);
        trip.setAmountOfKm(100);
        HashMap<String, Double> result = trip.calculateKmPerPerson();
        assertEquals(3, result.size());
        assertTrue(result.containsKey("person1"));
        assertTrue(result.containsKey("person2"));
        assertTrue(result.containsKey("person3"));
        assertEquals(33.3, result.get("person1"));
        assertEquals(33.3, result.get("person2"));
        assertEquals(33.3, result.get("person3"));
    }

    @Test
    @DisplayName("calculateKmPerPerson four persons")
    public void calculateKmPerPersonFour() {
        trip.addPerson(person1);
        trip.addPerson(person2);
        trip.addPerson(person3);
        trip.addPerson(person4);
        trip.setAmountOfKm(100);
        HashMap<String, Double> result = trip.calculateKmPerPerson();
        assertEquals(4, result.size());
        assertTrue(result.containsKey("person1"));
        assertTrue(result.containsKey("person2"));
        assertTrue(result.containsKey("person3"));
        assertTrue(result.containsKey("person4"));
        assertEquals(25.0, result.get("person1"));
        assertEquals(25.0, result.get("person2"));
        assertEquals(25.0, result.get("person3"));
        assertEquals(25.0, result.get("person4"));
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
