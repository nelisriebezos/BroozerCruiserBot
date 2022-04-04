package com.nelisriebezos.broozercruiserbot.domaintest.classtest;

import com.nelisriebezos.broozercruiserbot.domain.Person;
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
    Person person1;
    Person person2;
    TankSession tankSession;
    Trip trip;

    @BeforeEach
    public void init() {
        person1 = Person.to("person1");
        person1.setId(1L);
        person2 = Person.to("person2");
        person2.setId(2L);
        tankSession = TankSession.to();
        trip = Trip.to(100);
        tankSession.addTrip(trip);
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
        List<Person> persons = List.of(person1);
        oneTripOnePerson.addTrip(oneTripOnePerson.createTrip(persons, 50));
        HashMap<String, Double> result = oneTripOnePerson.calculateKmPerPerson();
        assertEquals(1, result.size());
        assertTrue(result.containsKey("person1"));
        assertEquals(50.0, result.get("person1"));
    }

    @Test
    @DisplayName("Test calculateDrivenKmPerPerson for two trips with same persons")
    public void calculateDrivenKmPerPersonThree() {

    }

    @Test
    @DisplayName("Test calculateDrivenKmPerPerson for two trips with different persons")
    public void calculateDrivenKmPerPersonFour() {

    }

    @Test
    public void calculateTotalAmountOfDrivenKm() {
        tankSession.addTrip(Trip.to(100));
        assertEquals(100, tankSession.calculateDrivenKm());
    }

    @Test
    public void createTrip() {
        List<Person> persons = new ArrayList<>();
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
