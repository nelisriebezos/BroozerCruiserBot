package com.nelisriebezos.broozercruiserbot.data.dto.connector;

import com.nelisriebezos.broozercruiserbot.data.dto.PersistCarDTO;
import com.nelisriebezos.broozercruiserbot.data.dto.PersistTankSessionDTO;
import com.nelisriebezos.broozercruiserbot.domain.Car;
import com.nelisriebezos.broozercruiserbot.domain.TankSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CarTankSessionConnector {
    public PersistCarDTO addTankSessionDTO() {
        return
    }

    public Car addTankSession() {

    }

    public PersistTankSessionDTO addCarDTO(PersistTankSessionDTO tankSessionDTO, Car car) {
        PersistCarDTO carDTO = PersistCarDTO.builder()
                .id(car.getId())
                .kmCounter(car.getKmCounter())
                .name(car.getName())
                .build();
        tankSessionDTO.setCar(carDTO);
        return tankSessionDTO;
    }

    public TankSession addCar(TankSession tankSession, PersistCarDTO carDTO) {
        Car car = Car.builder()
                .id(carDTO.getId())
                .kmCounter(carDTO.getKmCounter())
                .na
    }
}
