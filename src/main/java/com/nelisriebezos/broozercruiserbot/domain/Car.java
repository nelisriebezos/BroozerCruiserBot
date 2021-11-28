package com.nelisriebezos.broozercruiserbot.domain;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @Column(name = "car_id", nullable = false)
    private Long id;
    private int mileage;
    @OneToOne
    @JoinColumn(name = "tanksession_id")
    private TankSession tankSession;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car() {
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
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

    public int caluclateAmountOfKm(int mileage) {
        return mileage - this.mileage;
    }
}
