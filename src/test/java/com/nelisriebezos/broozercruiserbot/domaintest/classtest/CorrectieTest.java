package com.nelisriebezos.broozercruiserbot.domaintest.classtest;

import com.nelisriebezos.broozercruiserbot.domain.Correctie;
import com.nelisriebezos.broozercruiserbot.domain.Person;
import com.nelisriebezos.broozercruiserbot.domain.TankSession;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

public class CorrectieTest {
    Correctie correctie = new Correctie();

    @Test
    public void testGetSetId() {
        correctie.setId(1L);
        assertEquals(1L, correctie.getId());
    }

    @Test
    public void testGetSetTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        correctie.setTimestamp(timestamp);
        assertEquals(timestamp, correctie.getTimestamp());
    }

    @Test
    public void testGetSetDistance() {
        correctie.setDistance(100);
        assertEquals(100, correctie.getDistance());
    }

    @Test
    public void testGetSetTankSession() {
        TankSession tankSession = new TankSession(1L);
        correctie.setTankSession(tankSession);
        assertEquals(tankSession, correctie.getTankSession());
    }

    @Test
    public void testGetSetPerson() {
        Person person = new Person(1L);
        correctie.setPerson(person);
        assertEquals(person, correctie.getPerson());
    }
}
