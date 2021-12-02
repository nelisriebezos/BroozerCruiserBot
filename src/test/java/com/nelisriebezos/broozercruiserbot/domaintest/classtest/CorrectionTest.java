package com.nelisriebezos.broozercruiserbot.domaintest.classtest;

import com.nelisriebezos.broozercruiserbot.domain.Correction;
import com.nelisriebezos.broozercruiserbot.domain.Person;
import com.nelisriebezos.broozercruiserbot.domain.TankSession;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

public class CorrectionTest {
    Correction correction = new Correction();

    @Test
    public void testGetSetId() {
        correction.setId(1L);
        assertEquals(1L, correction.getId());
    }

    @Test
    public void testGetSetTimestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        correction.setTimestamp(timestamp);
        assertEquals(timestamp, correction.getTimestamp());
    }

    @Test
    public void testGetSetDistance() {
        correction.setDistance(100);
        assertEquals(100, correction.getDistance());
    }

    @Test
    public void testGetSetTankSession() {
        TankSession tankSession = new TankSession(1L);
        correction.setTankSession(tankSession);
        assertEquals(tankSession, correction.getTankSession());
    }

    @Test
    public void testGetSetPerson() {
        Person person = new Person(1L);
        correction.setPerson(person);
        assertEquals(person, correction.getPerson());
    }
}
