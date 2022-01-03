package com.nelisriebezos.broozercruiserbot.domain.domainclasses;

import java.sql.Timestamp;
import java.util.Objects;

public class Correction {

//    under construction

    private Long id;
    private Timestamp timestamp;
    private int distance;
    private TankSession tankSession;
    private Person person;

    public Correction() {
    }

    public Correction(Long id) {
        this.id = id;
    }

    public Correction(Timestamp timestamp, int distance, TankSession tankSession, Person person) {
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

    public Long getTankSessionId() {
        return tankSession.getId();
    }

    public Long getPersonId() {
        return person.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Correction)) return false;
        Correction that = (Correction) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Correction{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", distance=" + distance +
                ", tankSession=" + tankSession +
                ", person=" + person +
                '}';
    }
}
