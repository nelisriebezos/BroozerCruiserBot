package com.nelisriebezos.broozercruiserbot.persistence.dao;

import com.nelisriebezos.broozercruiserbot.domain.Car;

import java.util.List;

public interface CarDAO {
    public boolean save(Car car);
    public boolean update(Car car);
    public boolean delete(Car car);
    public Car findById(int id);
    public List<Car> findAll();
}
