package com.nelisriebezos.broozercruiserbot.persistence.querytest.cartest;

import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Car;
import com.nelisriebezos.broozercruiserbot.persistence.util.DatabaseTest;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class CarCrudTest extends DatabaseTest {
    @Test
    public void createPositive() throws Exception {
        car.setKmCounter(100);
        Car createdCar = carDAO.create(car);
        assertEquals(createdCar, carDAO.findById(createdCar.getId()));
    }

    @Test
    public void createNegativeKmCounter() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            car.setKmCounter(-3);
            carDAO.create(car);
        });
    }

    @Test
    public void updatePositive() throws DatabaseException {
        car.setKmCounter(100);
        Car createdcar = carDAO.create(car);
        createdcar.setKmCounter(150);
        Car updatedCar = carDAO.update(createdcar);
        assertEquals(updatedCar, carDAO.findById(createdcar.getId()));
    }

    @Test
    public void updateNegative() {
        Assertions.assertThrows(DatabaseException.class, () ->
                carDAO.update(car));
    }

    @Test
    public void deletePositive() throws DatabaseException {
        car.setKmCounter(100);
        Car createdcar = carDAO.create(car);
        carDAO.delete(createdcar.getId());

        Assertions.assertThrows(DatabaseException.class, () ->
                carDAO.findById(createdcar.getId()));
    }

    @Test
    public void findByIdPositive() throws DatabaseException {
        car.setKmCounter(100);
        Car createdcar = carDAO.create(car);
        assertEquals(createdcar, carDAO.findById(createdcar.getId()));
    }

    @Test
    public void findByIdNegative() {
        Assertions.assertThrows(DatabaseException.class, () ->
                carDAO.findById(29L));
    }
}
