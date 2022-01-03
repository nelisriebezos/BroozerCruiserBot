package com.nelisriebezos.broozercruiserbot.domaintest.classtest;

import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Correction;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Person;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.TankSession;
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
        TankSession tankSession = new TankSession();
        tankSession.setId(1L);
        correction.setTankSession(tankSession);
        assertEquals(tankSession, correction.getTankSession());
    }

    @Test
    public void testGetSetPerson() {
        Person person = new Person();
        person.setId(1L);
        correction.setPerson(person);
        assertEquals(person, correction.getPerson());
    }
}
