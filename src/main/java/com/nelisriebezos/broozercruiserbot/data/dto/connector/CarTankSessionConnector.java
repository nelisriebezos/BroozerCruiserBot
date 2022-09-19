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
    public PersistCarDTO addTankSessionDTO(PersistCarDTO carDTO, TankSession tankSession) {
        PersistTankSessionDTO tankSessionDTO = PersistTankSessionDTO.builder()
                .id(tankSession.getId())
                .date(tankSession.getDate())
                //trips
                .car(carDTO)
                .build();

    }

    public Car addTankSession(Car car, PersistTankSessionDTO tankSessionDTO) {

    }

    public PersistTankSessionDTO addCarDTO(PersistTankSessionDTO tankSessionDTO, Car car) {
        PersistCarDTO carDTO = PersistCarDTO.builder()
                .id(car.getId())
                .kmCounter(car.getKmCounter())
                .name(car.getName())
                .build();
        //add tanksessiondto with method above
        carDTO.addTankSessionDTO(tankSessionDTO);

        tankSessionDTO.setCar(carDTO);
        return tankSessionDTO;
    }

    public TankSession addCar(TankSession tankSession, PersistCarDTO carDTO) {
        Car car = Car.builder()
                .id(carDTO.getId())
                .kmCounter(carDTO.getKmCounter())
                .name(carDTO.getName())
                .build();
        //addtanksession to car, see method above
        car.addTanksession(tankSession);

        tankSession.setCar(car);
        return tankSession;
    }
}
