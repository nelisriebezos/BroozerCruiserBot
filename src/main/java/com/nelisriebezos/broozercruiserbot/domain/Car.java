package com.nelisriebezos.broozercruiserbot.domain;


import com.nelisriebezos.broozercruiserbot.utils.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int kmCounter;

    @OneToMany(mappedBy = "car",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    List<TankSession> tankSessionList = new ArrayList<>();

    public Car(int kmCounter) {
        this.kmCounter = kmCounter;
    }

    public TankSession createTankSession() {
        return new TankSession(new Date(), this);
    }

    public Trip createTrip(List<Person> personList, int amountOfDrivenKm, TankSession tankSession) {
        Trip trip = new Trip(new Date(), tankSession);
        for (Person person : personList) trip.addPerson(person);
        trip.setAmountOfKm(amountOfDrivenKm);
        return trip;
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
        return tankSessionList.get(tankSessionList.size() - 1);
    }

    @Override
    @Generated
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Objects.equals(getId(), car.getId());
    }

    @Override
    @Generated
    public int hashCode() {
        return Objects.hash(getId(), getKmCounter(), tankSessionList);
    }
}
