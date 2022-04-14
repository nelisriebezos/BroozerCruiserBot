package com.nelisriebezos.broozercruiserbot.domain;

import com.nelisriebezos.broozercruiserbot.utils.Exceptions.CruiserException;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {
    private Long id;
    private int kmCounter = 0;
    private String name;
    @Builder.Default
    @ToString.Exclude
    private List<TankSession> tankSessionList = new ArrayList<>();

    public static Car to(int kmCounter) {
        return new Car(kmCounter);
    }

    public Car(int kmCounter) {
        this.kmCounter = kmCounter;
    }

    public TankSession createTankSession() {
        return new TankSession();
    }

    public int calculateTripAmountOfDrivenKm(int kmCounter) {
        return kmCounter - this.kmCounter;
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

    public TankSession getCurrentTanksession() {
        if (tankSessionList.size() == 0) throw new CruiserException("There are no tanksessions");
        return tankSessionList.get(tankSessionList.size() - 1);
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
