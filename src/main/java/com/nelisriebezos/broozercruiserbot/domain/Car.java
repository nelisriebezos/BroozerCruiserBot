package com.nelisriebezos.broozercruiserbot.domain;


import java.util.ArrayList;
import java.util.List;

public class Car {
    private Long id;
    private int kmCounter;
    List<TankSession> tankSessionList = new ArrayList<>();

    public Car() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getKmCounter() {
        return kmCounter;
    }

    public void setKmCounter(int kmCounter) {
        this.kmCounter = kmCounter;
    }

    public List<TankSession> getTankSessionList() {
        return tankSessionList;
    }

    public void setTankSessionList(List<TankSession> tankSessionList) {
        this.tankSessionList = tankSessionList;
    }

    public boolean addTankSession(TankSession tankSession) {
        if (!tankSessionList.contains(tankSession)) {
            tankSessionList.add(tankSession);
            return true;
        }
        return false;
    }
}
