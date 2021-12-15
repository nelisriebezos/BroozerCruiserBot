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
    public void createNegative() {
        Assertions.assertThrows(DatabaseException.class, () -> {
            car.setKmCounter(-3);
            Car createdCar = carService.create(car);
            System.out.println(carService.findById(createdCar.getId()));
        });
    }

    @Test
    public void updatePositive() throws Exception {
        car.setKmCounter(100);
        Car createdcar = carService.create(car);

        createdcar.setKmCounter(150);
        carService.update(createdcar);
        assertEquals(150, carService.findById(createdcar.getId()).getKmCounter());
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

        Assertions.assertThrows(Exception.class, () ->
                System.out.println(carService.findById(createdcar.getId())));
    }
}
