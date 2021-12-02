package com.nelisriebezos.broozercruiserbot.domain;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Trip {
    private Long id;
    private int distance;
    private Timestamp timestamp;
    private TankSession tankSession;
    List<Person> personList = new ArrayList<>();

    public Trip() {
    }

    public Trip(Long id) {
        this.id = id;
    }

    public Trip(int distance, Timestamp timestamp, TankSession tankSession, List<Person> personList) {
        this.distance = distance;
        this.timestamp = timestamp;
        this.tankSession = tankSession;
        this.personList = personList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public TankSession getTankSession() {
        return tankSession;
    }

    public void setTankSession(TankSession tankSession) {
        this.tankSession = tankSession;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public boolean addPerson(Person person) {
        if (!personList.contains(person)) {
            personList.add(person);
            return true;
        }
        return false;
    }

    public void removePerson(Person person) {
        personList.remove(person);
    }
}
