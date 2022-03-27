package com.nelisriebezos.broozercruiserbot.domaintest.classtest;

import com.nelisriebezos.broozercruiserbot.domain.domain.Car;
import com.nelisriebezos.broozercruiserbot.domain.domain.TankSession;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CarTest {
    Car car = new Car();

    @Test
    public void testGetSetId() {
        car.setId(1L);
        assertEquals(1L, (long) car.getId());
    }

    @Test
    public void testGetSetKmCounter() {
        car.setKmCounter(100);
        assertEquals(100, car.getKmCounter());
    }

    @Test
    public void testGetTankSessionList() {
        List<TankSession> tanksessionList = new ArrayList<>();
        assertEquals(tanksessionList, car.getTankSessionList());
    }

    @Test
    public void addTankSession() {
        TankSession tankSession = new TankSession();
        tankSession.setId(1L);
        assertTrue(car.addTanksession(tankSession));
    }

    @Test
    public void addExistingTankSession() {
        TankSession tankSession = new TankSession();
        tankSession.setId(1L);
        car.addTanksession(tankSession);
        assertFalse(car.addTanksession(tankSession));
    }

    @Test
    public void removeTankSession() {
        TankSession tankSession = new TankSession();
        tankSession.setId(1L);
        car.addTanksession(tankSession);
        car.removeTanksession(tankSession);
        assertEquals(0, car.getTankSessionList().size());
    }
}
