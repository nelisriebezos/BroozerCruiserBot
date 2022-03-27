package com.nelisriebezos.broozercruiserbot.domain.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter @Setter @ToString @NoArgsConstructor
public class Car {
    private Long id;
    private int kmCounter;
    List<TankSession> tankSessionList = new ArrayList<>();

    public Car(int kmCounter) {
        this.kmCounter = kmCounter;
    }

    public boolean addTanksession(TankSession tankSession) {
        if (!tankSessionList.contains(tankSession)) {
            tankSessionList.add(tankSession);
            return true;
        }
        return false;
    }

    public void removeTanksession(TankSession tankSession) {
        tankSessionList.remove(tankSession);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Objects.equals(getId(), car.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getKmCounter(), tankSessionList);
    }
}
