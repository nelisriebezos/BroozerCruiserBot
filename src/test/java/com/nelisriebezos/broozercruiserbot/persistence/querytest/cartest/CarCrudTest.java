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
        Car createdCar = carService.create(car);
        assertEquals(createdCar, carService.findById(createdCar.getId()));
    }

    @Test
    public void createNegativeKmCounter() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            car.setKmCounter(-3);
            carService.create(car);
        });
    }

    @Test
    public void updatePositive() throws DatabaseException {
        car.setKmCounter(100);
        Car createdcar = carService.create(car);
        createdcar.setKmCounter(150);
        Car updatedCar = carService.update(createdcar);
        assertEquals(updatedCar, carService.findById(createdcar.getId()));
    }

    @Test
    public void updateNegative() {
        Assertions.assertThrows(DatabaseException.class, () ->
                carService.update(car));
    }

    @Test
    public void deletePositive() throws DatabaseException {
        car.setKmCounter(100);
        Car createdcar = carService.create(car);
        carService.delete(createdcar.getId());

        Assertions.assertThrows(DatabaseException.class, () ->
                carService.findById(createdcar.getId()));
    }

    @Test
    public void findByIdPositive() throws DatabaseException {
        car.setKmCounter(100);
        Car createdcar = carService.create(car);
        assertEquals(createdcar, carService.findById(createdcar.getId()));
    }

    @Test
    public void findByIdNegative() {
        Assertions.assertThrows(DatabaseException.class, () ->
                carService.findById(29L));
    }
}
