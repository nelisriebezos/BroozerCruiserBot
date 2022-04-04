package com.nelisriebezos.broozercruiserbot.domaintest.classtest;

import com.nelisriebezos.broozercruiserbot.domain.Car;
import com.nelisriebezos.broozercruiserbot.domain.Person;
import com.nelisriebezos.broozercruiserbot.domain.TankSession;
import com.nelisriebezos.broozercruiserbot.utils.Exceptions.CruiserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


public class CarTest {
    Car car;
    Person person1;
    Person person2;
    TankSession tankSession;

    @BeforeEach
    public void init() {
        car = Car.to(1000);
        person1 = Person.to("person1");
        person2 = Person.to("person2");
        tankSession = TankSession.to();
    }

    @Test
    public void createTankSession() {
        TankSession tankSession = car.createTankSession();
        assertEquals(new Date(), tankSession.getDate());
    }

    @Test
    public void calculateTripAmountOfDrivenKm() {
        assertEquals(500, car.calculateTripAmountOfDrivenKm(1500));
    }

    @Test
    public void getCurrentTanksession() {
        car.addTanksession(tankSession);
        assertEquals(tankSession, car.getCurrentTanksession());
    }

    @Test
    public void getCurrentTanksessionNoneSaved() {
        assertThrows(CruiserException.class, () -> car.getCurrentTanksession());
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
