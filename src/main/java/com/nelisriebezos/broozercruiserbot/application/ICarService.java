package com.nelisriebezos.broozercruiserbot.application;

import com.nelisriebezos.broozercruiserbot.domain.Car;

public interface ICarService {
    public Car getCar(Long id);
    public Long getCarIdByName(String name);
    public Car persistCar(Car car);
    public boolean deleteCar(Car car);
}
