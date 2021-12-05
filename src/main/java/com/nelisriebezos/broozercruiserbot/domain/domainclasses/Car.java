package com.nelisriebezos.broozercruiserbot.domain.domainclasses;


import java.util.ArrayList;
import java.util.List;

public class Car {
    private Long id;
    private int kmCounter;
    List<TankSession> tankSessionList = new ArrayList<>();

    public Car() {
    }

    public Car(Long id) {
        this.id = id;
    }

    public Car(Long id, int kmcounter) {
        this.id = id;
        this.kmCounter = kmcounter;
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

    public List<TankSession> getTanksessionList() {
        return tankSessionList;
    }

    public void setTanksessionList(List<TankSession> tankSessionList) {
        this.tankSessionList = tankSessionList;
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
}
