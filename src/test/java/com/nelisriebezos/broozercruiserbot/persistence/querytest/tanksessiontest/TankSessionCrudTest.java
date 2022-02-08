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
        TankSession createdTanksession = tankSessionDAO.create(tankSession);
        assertEquals(createdTanksession, tankSessionDAO.findById(createdTanksession.getId()));
    }

    @Test
    public void createNegativeTimestamp() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            car.setId(1L);
            tankSession.setCar(car);
            tankSessionDAO.create(tankSession);
        });
    }

    @Test
    public void createNegativeCar() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            tankSession.setDate(date1);
            tankSessionDAO.create(tankSession);
        });
    }

    @Test
    public void updatePositive() throws DatabaseException {
        car.setId(1L);
        tankSession.setCar(car);
        tankSession.setDate(date1);
        TankSession createdTanksession = tankSessionDAO.create(tankSession);
        createdTanksession.setDate(date2);
        TankSession updatedTanksession = tankSessionDAO.update(createdTanksession);
        assertEquals(updatedTanksession, tankSessionDAO.findById(createdTanksession.getId()));
    }

    @Test
    public void updateNegative() {
        Assertions.assertThrows(DatabaseException.class, () ->
                tankSessionDAO.update(tankSession)
                );
    }

    @Test
    public void delete() throws DatabaseException {
        car.setId(1L);
        tankSession.setCar(car);
        tankSession.setDate(date1);
        TankSession createdTanksession = tankSessionDAO.create(tankSession);
        tankSessionDAO.delete(createdTanksession.getId());

        Assertions.assertThrows(DatabaseException.class, () ->
                tankSessionDAO.findById(createdTanksession.getId())
        );
    }

    @Test
    public void findByIdPositive() throws DatabaseException {
        car.setId(1L);
        tankSession.setCar(car);
        tankSession.setDate(date1);
        TankSession createdTanksession = tankSessionDAO.create(tankSession);
        assertEquals(createdTanksession, tankSessionDAO.findById(createdTanksession.getId()));
    }

    @Test
    public void findByIdNegative() {
        Assertions.assertThrows(DatabaseException.class, () ->
                tankSessionDAO.findById(null));
    }

    @Test
    public void findAll() throws DatabaseException {
        car.setId(1L);
        tankSession.setCar(car);
        tankSession.setDate(date1);
        tankSessionDAO.create(tankSession);
        assertEquals(2, tankSessionDAO.findAll().size());
    }

    @Test
    public void findByCarId() throws DatabaseException {
        List<TankSession> tankSessionList = new ArrayList<>();
        tankSessionList.add(tankSessionDAO.findById(1L));
        assertEquals(tankSessionList, tankSessionDAO.findTankSessionsByCarId(1L));
    }
}
