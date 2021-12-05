package com.nelisriebezos.broozercruiserbot.persistence.querytest.cartest;

import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Car;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserDB;
import com.nelisriebezos.broozercruiserbot.domain.service.CarService;
import com.nelisriebezos.broozercruiserbot.persistence.util.DatabaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class CarCrudTest extends DatabaseTest {
    CruiserDB cruiserDB;
    CarService carService;
    Car car;

    @BeforeEach
    public void init() throws IOException, DatabaseException {
        cruiserDB = getCruiserDB();
        carService = new CarService(cruiserDB);
        car = new Car();
    }

    @Test
    public void createPositive() {
        car.setKmCounter(100);
        Car createdCar = carService.create(car);
        assertEquals(createdCar, carService.findById(createdCar.getId()));
    }

    @Test
    public void createNegative() {
        DatabaseException thrown = Assertions.assertThrows(DatabaseException.class, () -> {
            carService.create(car);
        });
        assertEquals("-error-", thrown.getMessage());
    }

    @Test
    public void updatePositive() {
        car.setKmCounter(100);
        Car createdcar = carService.create(car);

        createdcar.setKmCounter(150);
        carService.update(createdcar);
        assertEquals(150, carService.findById(createdcar.getId()).getKmCounter());
    }

    @Test
    public void updateNegative() {
        DatabaseException thrown = Assertions.assertThrows(DatabaseException.class, () -> {
           carService.update(car);
        });
        assertEquals("-error-", thrown.getMessage());
    }

    @Test
    public void deletePositive() {
        car.setKmCounter(100);
        Car createdcar = carService.create(car);
        carService.delete(createdcar.getId());

        DatabaseException thrown = Assertions.assertThrows(DatabaseException.class, () -> {
           carService.findById(createdcar.getId());
        });
        assertEquals("-error-", thrown.getMessage());
    }

    @Test
    public void deleteNegative() {
        DatabaseException thrown = Assertions.assertThrows(DatabaseException.class, () -> {
            carService.delete(1L);
        });
        assertEquals("-error-", thrown.getMessage());
    }
}
