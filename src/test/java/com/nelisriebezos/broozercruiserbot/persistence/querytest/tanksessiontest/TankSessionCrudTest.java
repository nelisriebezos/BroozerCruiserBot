package com.nelisriebezos.broozercruiserbot.persistence.querytest.tanksessiontest;

import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.TankSession;
import com.nelisriebezos.broozercruiserbot.persistence.util.DatabaseTest;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TankSessionCrudTest extends DatabaseTest {
    @Test
    public void createPositive() throws Exception {
        car.setId(1L);
        tankSession.setCar(car);
        tankSession.setDate(date1);
        TankSession createdTanksession = tankSessionService.create(tankSession);
        assertEquals(createdTanksession, tankSessionService.findById(createdTanksession.getId()));
    }

    @Test
    public void createNegativeTimestamp() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            car.setId(1L);
            tankSession.setCar(car);
            tankSessionService.create(tankSession);
        });
    }

    @Test
    public void createNegativeCar() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            tankSession.setDate(date1);
            tankSessionService.create(tankSession);
        });
    }

    @Test
    public void updatePositive() throws DatabaseException {
        car.setId(1L);
        tankSession.setCar(car);
        tankSession.setDate(date1);
        TankSession createdTanksession = tankSessionService.create(tankSession);
        createdTanksession.setDate(date2);
        TankSession updatedTanksession = tankSessionService.update(createdTanksession);
        assertEquals(updatedTanksession, tankSessionService.findById(createdTanksession.getId()));
    }

    @Test
    public void updateNegative() {
        Assertions.assertThrows(DatabaseException.class, () ->
                tankSessionService.update(tankSession)
                );
    }

    @Test
    public void delete() throws DatabaseException {
        car.setId(1L);
        tankSession.setCar(car);
        tankSession.setDate(date1);
        TankSession createdTanksession = tankSessionService.create(tankSession);
        tankSessionService.delete(createdTanksession.getId());

        Assertions.assertThrows(DatabaseException.class, () ->
                tankSessionService.findById(createdTanksession.getId())
        );
    }

    @Test
    public void findByIdPositive() throws DatabaseException {
        car.setId(1L);
        tankSession.setCar(car);
        tankSession.setDate(date1);
        TankSession createdTanksession = tankSessionService.create(tankSession);
        assertEquals(createdTanksession, tankSessionService.findById(createdTanksession.getId()));
    }

    @Test
    public void findByIdNegative() {
        Assertions.assertThrows(DatabaseException.class, () ->
                tankSessionService.findById(null));
    }

    @Test
    public void findAll() throws DatabaseException {
        car.setId(1L);
        tankSession.setCar(car);
        tankSession.setDate(date1);
        tankSessionService.create(tankSession);
        assertEquals(2, tankSessionService.findAll().size());
    }

    @Test
    public void findByCarId() throws DatabaseException {
        List<TankSession> tankSessionList = new ArrayList<>();
        tankSessionList.add(tankSessionService.findById(1L));
        assertEquals(tankSessionList, tankSessionService.findTankSessionsByCarId(1L));
    }
}
