package com.nelisriebezos.broozercruiserbot.application;

import com.nelisriebezos.broozercruiserbot.data.CarRepository;
import com.nelisriebezos.broozercruiserbot.data.dto.mapper.PersistCarDTOMapper;
import com.nelisriebezos.broozercruiserbot.domain.Car;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@AllArgsConstructor
public class CarService implements ICarService {
    private final CarRepository carRepository;
    private final PersistCarDTOMapper carDTOMapper;

    @Override
    public Car getCar(Long id) {
        return this.carDTOMapper.fromDTO(this.carRepository.getById(id));
    }

    @Override
    public Long getCarIdByName(String name) {
        return this.carRepository.findCarIdByName(name);
    }


    @Override
    public Car persistCar(Car car) {
        return this.carDTOMapper.fromDTO(this.carRepository.save(this.carDTOMapper.toDTO(car)));
    }

    @Override
    public boolean deleteCar(Car car) {
        this.carRepository.delete(this.carDTOMapper.toDTO(car));
        return true;
    }
}
