package com.nelisriebezos.broozercruiserbot.domain;

import java.sql.Timestamp;

public class Correctie {
    private Long id;
    private Timestamp timestamp;
    private int distance;
    private TankSession tankSession;
    private Person person;

    public Correctie() {
    }

    public Correctie(Long id) {
        this.id = id;
    }

    public Correctie(Timestamp timestamp, int distance, TankSession tankSession, Person person) {
        this.timestamp = timestamp;
        this.distance = distance;
        this.tankSession = tankSession;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public TankSession getTankSession() {
        return tankSession;
    }

    public void setTankSession(TankSession tankSession) {
        this.tankSession = tankSession;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
