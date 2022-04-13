package com.nelisriebezos.broozercruiserbot.domaintest.classtest;

import com.nelisriebezos.broozercruiserbot.domain.TankSession;
import com.nelisriebezos.broozercruiserbot.domain.Trip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TankSessionTest {
    String person1;
    String person2;
    TankSession tankSession;
    Trip trip;

    @BeforeEach
    public void init() {
        person1 = "person1";
        person2 = "person2";
        tankSession = TankSession.to();
        trip = Trip.to(100);
        tankSession.addTrip(trip);
    }

    @Test
    @DisplayName("calculatePricePerPerson one person")
    public void calculatePricePerPersonOne() {
        HashMap<String, Double> testMap = new HashMap<>();
        testMap.put("person1", 100.0);
        HashMap<String, Double> result = tankSession.calculatePricePerPerson(400, 100, testMap);
        assertEquals(1, result.size());
        assertTrue(result.containsKey("person1"));
        assertEquals(400.0, result.get("person1"));
    }

    @Test
    @DisplayName("calculatePricePerPerson two persons")
    public void calculatePricePerPersonTwo() {
        HashMap<String, Double> testMap = new HashMap<>();
        testMap.put("person1", 50.0);
        testMap.put("person2", 50.0);
        HashMap<String, Double> result = tankSession.calculatePricePerPerson(400, 100, testMap);
        assertEquals(2, result.size());
        assertTrue(result.containsKey("person1"));
        assertTrue(result.containsKey("person2"));
        assertEquals(200.0, result.get("person1"));
        assertEquals(200.0, result.get("person2"));
    }

    @Test
    @DisplayName("calculatePricePerPerson three persons")
    public void calculatePricePerPersonThree() {
        HashMap<String, Double> testMap = new HashMap<>();
        testMap.put("person1", 33.3);
        testMap.put("person2", 33.3);
        testMap.put("person3", 33.3);
        HashMap<String, Double> result = tankSession.calculatePricePerPerson(400, 100, testMap);
        assertEquals(3, result.size());
        assertTrue(result.containsKey("person1"));
        assertTrue(result.containsKey("person2"));
        assertTrue(result.containsKey("person3"));
        assertEquals(133.2, result.get("person1"));
        assertEquals(133.2, result.get("person2"));
        assertEquals(133.2, result.get("person3"));
    }

    @Test
    @DisplayName("calculatePricePerPerson four persons")
    public void calculatePricePerPersonFour() {
        HashMap<String, Double> testMap = new HashMap<>();
        testMap.put("person1", 25.0);
        testMap.put("person2", 25.0);
        testMap.put("person3", 25.0);
        testMap.put("person4", 25.0);
        HashMap<String, Double> result = tankSession.calculatePricePerPerson(400, 100, testMap);
        assertEquals(4, result.size());
        assertTrue(result.containsKey("person1"));
        assertTrue(result.containsKey("person2"));
        assertTrue(result.containsKey("person3"));
        assertTrue(result.containsKey("person4"));
        assertEquals(100.0, result.get("person1"));
        assertEquals(100.0, result.get("person2"));
        assertEquals(100.0, result.get("person3"));
        assertEquals(100.0, result.get("person4"));
    }

    @Test
    @DisplayName("Test calculateDrivenKmPerPerson for no trip")
    public void calculateDrivenKmPerPersonOne() {
        TankSession noTrips = TankSession.to();
        assertTrue(noTrips.calculateKmPerPerson().isEmpty());
    }

    @Test
    @DisplayName("Test calculateDrivenKmPerPerson for one trip with two persons")
    public void calculateDrivenKmPerPersonTwo() {
        TankSession oneTripOnePerson = TankSession.to();
        List<String> persons = List.of(person1);
        oneTripOnePerson.addTrip(oneTripOnePerson.createTrip(persons, 50));
        HashMap<String, Double> result = oneTripOnePerson.calculateKmPerPerson();
        assertEquals(1, result.size());
        assertTrue(result.containsKey("person1"));
        assertEquals(50.0, result.get("person1"));
    }

    @Test
    @DisplayName("Test calculateDrivenKmPerPerson for two trips with same persons")
    public void calculateDrivenKmPerPersonThree() {
        TankSession twoTripsOnePerson = TankSession.to();
        List<String> persons = List.of(person1);
        Trip trip1 = twoTripsOnePerson.createTrip(persons, 50);
        trip1.setId(1L);
        Trip trip2 = twoTripsOnePerson.createTrip(persons, 50);
        trip2.setId(2L);
        twoTripsOnePerson.addTrip(trip1);
        twoTripsOnePerson.addTrip(trip2);
        HashMap<String, Double> result = twoTripsOnePerson.calculateKmPerPerson();
        assertEquals(1, result.size());
        assertTrue(result.containsKey("person1"));
        assertEquals(100.0, result.get("person1"));
    }

    @Test
    @DisplayName("Test calculateDrivenKmPerPerson for two trips with different persons")
    public void calculateDrivenKmPerPersonFour() {
        TankSession twoTripsTwoPerson = TankSession.to();
        List<String> persons1 = List.of(person1);
        List<String> persons2 = List.of(person2);
        Trip trip1 = twoTripsTwoPerson.createTrip(persons1, 50);
        trip1.setId(1L);
        Trip trip2 = twoTripsTwoPerson.createTrip(persons2, 50);
        trip2.setId(2L);
        twoTripsTwoPerson.addTrip(trip1);
        twoTripsTwoPerson.addTrip(trip2);
        HashMap<String, Double> result = twoTripsTwoPerson.calculateKmPerPerson();
        assertEquals(2, result.size());
        assertTrue(result.containsKey("person1"));
        assertTrue(result.containsKey("person2"));
        assertEquals(50.0, result.get("person1"));
        assertEquals(50.0, result.get("person2"));
    }

    @Test
    public void calculateTotalAmountOfDrivenKm() {
        tankSession.addTrip(Trip.to(100));
        assertEquals(100, tankSession.calculateDrivenKm());
    }

    @Test
    public void createTrip() {
        List<String> persons = new ArrayList<>();
        persons.add(person1);
        persons.add(person2);
        Trip trip = tankSession.createTrip(persons, 50);
        assertEquals(persons, trip.getPersonList());
        assertEquals(new Date(), trip.getDate());
        assertEquals(50, trip.getAmountOfKm());
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
        tankSession.removeTrip(trip);
        assertEquals(0, tankSession.getTripList().size());
    }
}
