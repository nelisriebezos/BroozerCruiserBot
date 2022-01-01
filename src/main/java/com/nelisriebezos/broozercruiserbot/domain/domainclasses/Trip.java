package com.nelisriebezos.broozercruiserbot.domain.domainclasses;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public Trip(long id, int distance, Timestamp timestamp) {
        this.id = id;
        this.distance = distance;
        this.timestamp = timestamp;
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

    public Long getTankSessionId() {
        return tankSession.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trip)) return false;
        Trip trip = (Trip) o;
        return Objects.equals(getId(), trip.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", distance=" + distance +
                ", timestamp=" + timestamp +
                ", tankSession=" + tankSession +
                ", personList=" + personList +
                '}';
    }
}
