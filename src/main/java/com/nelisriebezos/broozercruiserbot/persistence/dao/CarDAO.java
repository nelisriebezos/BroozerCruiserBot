package com.nelisriebezos.broozercruiserbot.persistence.dao;

import com.nelisriebezos.broozercruiserbot.domain.Car;

public interface CarDAO {
    public boolean save(Car car);
    public boolean update(Car car);
    public boolean delete(Car car);
    public Car findById(int id);
}
