package com.nelisriebezos.broozercruiserbot.persistence.querytest.cartest;

import com.nelisriebezos.broozercruiserbot.Exceptions.CarException;
import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Car;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserDB;
import com.nelisriebezos.broozercruiserbot.domain.service.CarService;
import com.nelisriebezos.broozercruiserbot.persistence.util.DatabaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

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
    public void createPositive() throws Exception {
        car.setKmCounter(100);
        Car createdCar = carService.create(car);
        assertEquals(createdCar, carService.findById(createdCar.getId()));
        cleanupTempFolder();
    }

    @Test
    public void createNegative() throws IOException {
        DatabaseException thrown = Assertions.assertThrows(DatabaseException.class, () -> {
            car.setKmCounter(-3);
            Car createdCar = carService.create(car);
            System.out.println(carService.findById(createdCar.getId()));
        });
        assertEquals("-error-", thrown.getMessage());
        cleanupTempFolder();
    }

    @Test
    public void updatePositive() throws Exception {
        car.setKmCounter(100);
        Car createdcar = carService.create(car);

        createdcar.setKmCounter(150);
        carService.update(createdcar);
        assertEquals(150, carService.findById(createdcar.getId()).getKmCounter());
        cleanupTempFolder();
    }

    @Test
    public void updateNegative() throws IOException {
        DatabaseException thrown = Assertions.assertThrows(DatabaseException.class, () -> {
           carService.update(car);
        });
        assertEquals("-error-", thrown.getMessage());
        cleanupTempFolder();
    }

    @Test
    public void deletePositive() throws IOException, SQLException, DatabaseException, CarException {
        car.setKmCounter(100);
        Car createdcar = carService.create(car);
        carService.delete(createdcar.getId());

        Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            System.out.println(carService.findById(createdcar.getId()));
        });
        assertEquals("FindById error: nothing was found, " + createdcar.getId(), thrown.getMessage());
        cleanupTempFolder();
    }

    @Test
    public void deleteNegative() throws IOException {
        DatabaseException thrown = Assertions.assertThrows(DatabaseException.class, () -> {
            carService.delete(1L);
        });
        assertEquals("-error-", thrown.getMessage());
        cleanupTempFolder();
    }
}
