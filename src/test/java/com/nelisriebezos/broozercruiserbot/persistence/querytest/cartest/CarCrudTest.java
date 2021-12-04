package com.nelisriebezos.broozercruiserbot.persistence.querytest.cartest;

import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.domain.Car;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserDB;
import com.nelisriebezos.broozercruiserbot.persistence.service.CarService;
import com.nelisriebezos.broozercruiserbot.persistence.util.DatabaseTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class CarCrudTest extends DatabaseTest {

    @Test
    public void createTestPositive() throws DatabaseException, IOException {
        CruiserDB cruiserDB = getCruiserDB();
        CarService carService = new CarService(cruiserDB);

        Car car = new Car();
        car.setKmCounter(100);

        carService.create(car);
        assertEquals(new Car(1L, 100), carService.findById(1L));
    }

    @Test
    public void createTestNegative() throws DatabaseException, IOException {
        CruiserDB cruiserDB = getCruiserDB();
        CarService carService = new CarService(cruiserDB);

        Car car = new Car();

        carService.create(car);

    }

//    create / read (positive + negative)
//    update / read (positive + negative)
//    delete / read (positive + negative)
}
