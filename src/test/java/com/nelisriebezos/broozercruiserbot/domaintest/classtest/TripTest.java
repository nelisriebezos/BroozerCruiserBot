package com.nelisriebezos.broozercruiserbot.domaintest.classtest;

import com.nelisriebezos.broozercruiserbot.domain.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class TripTest {
    Trip trip;
    String person1;
    String person2;
    String person3;
    String person4;

    @BeforeEach
    public void init() {
        trip = new Trip();
        person1 = "person1";
        person2 = "person2";
        person3 = "person3";
        person4 = "person4";
    }

    @Test
    @DisplayName("calculateKmPerPerson one person")
    public void calculateKmPerPersonOne() {
        trip.addPersonName(person1);
        trip.setAmountOfKm(100);
        HashMap<String, Double> result = trip.calculateKmPerPerson();
        assertEquals(1, result.size());
        assertTrue(result.containsKey("person1"));
        assertEquals(100.0, result.get("person1"));
    }

    @Test
    @DisplayName("calculateKmPerPerson two persons")
    public void calculateKmPerPersonTwo() {
        trip.addPersonName(person1);
        trip.addPersonName(person2);
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
        trip.addPersonName(person1);
        trip.addPersonName(person2);
        trip.addPersonName(person3);
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
        trip.addPersonName(person1);
        trip.addPersonName(person2);
        trip.addPersonName(person3);
        trip.addPersonName(person4);
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
        assertTrue(trip.addPersonName("person1"));
    }

    @Test
    public void addExistingPerson() {
        trip.addPersonName("person1");
        assertFalse(trip.addPersonName("person1"));
    }

    @Test
    public void removePerson() {
        trip.addPersonName("person1");
        trip.removePerson("person1");
        assertEquals(0, trip.getPersonList().size());
    }
}
