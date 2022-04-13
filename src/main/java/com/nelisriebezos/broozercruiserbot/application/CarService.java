package com.nelisriebezos.broozercruiserbot.application;

import com.nelisriebezos.broozercruiserbot.data.CarRepository;
import com.nelisriebezos.broozercruiserbot.domain.Car;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@AllArgsConstructor
public class CarService implements ICarService {
    private final CarRepository carRepository;

    @Override
    public Car getCar(Long id) {
        return this.carRepository.getById(id);
    }

    @Override
    public Car persistCar(Car car) {
        return this.carRepository.save(car);
    }

    @Override
    public boolean deleteCar(Car car) {
        this.carRepository.delete(car);
        return true;
    }
}
