package com.nelisriebezos.broozercruiserbot.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private int distance;
    @OneToOne
    @JoinColumn(name = "tanksession_id")
    private TankSession tankSession;
    @OneToMany(mappedBy = "car")
    private List<Rit> ritList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car() {
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int mileage) {
        this.distance = mileage;
    }

    public TankSession getTankSession() {
        return tankSession;
    }

    public void setTankSession(TankSession tankSession) {
        this.tankSession = tankSession;
    }

    public int getSessionKmAmount() {
        return tankSession.getAmountOfKm();
    }

    public void addSessionKmAmount(int amountOfKm) {
        tankSession.addAmountOfKm(amountOfKm);
    }

    public int calculateAmountOfKm(int mileage) {
        return mileage - this.distance;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", mileage=" + distance +
                ", tankSession=" + tankSession.getId() +
                '}';
    }
}
