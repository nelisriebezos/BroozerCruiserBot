package com.nelisriebezos.broozercruiserbot.domain;

public class Car {
    private int mileage;
    private TankSession tankSession;

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
