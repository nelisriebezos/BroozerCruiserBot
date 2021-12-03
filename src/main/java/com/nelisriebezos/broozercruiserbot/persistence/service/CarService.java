package com.nelisriebezos.broozercruiserbot.persistence.service;

import com.nelisriebezos.broozercruiserbot.domain.Car;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserDB;
import org.checkerframework.checker.units.qual.C;

public class CarService {
    private CruiserDB cruiserDB;

    public CarService(CruiserDB cruiserDB) {
        this.cruiserDB = cruiserDB;
    }

    public void create(Car car) {

    }

    public void update(Car car) {

    }

    public void delete(Car car) {

    }

    public Car findById(Long id) {
        return null;
    }
}
