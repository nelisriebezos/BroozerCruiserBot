package com.nelisriebezos.broozercruiserbot.domain.domainclasses;


import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Trip {
    private Long id;
    private int distance;
    private Date date;
    private TankSession tankSession;
    List<Person> personList = new ArrayList<>();

    public Trip() {
    }

    public Trip(Long id) {
        this.id = id;
    }

    public Trip(int distance, Date date, TankSession tankSession, List<Person> personList) {
        this.distance = distance;
        this.date = date;
        this.tankSession = tankSession;
        this.personList = personList;
    }

    public Trip(long id, int distance, Date date) {
        this.id = id;
        this.distance = distance;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TankSession getTankSession() {
        return tankSession;
    }

    public void setTankSession(TankSession tankSession) {
        this.tankSession = tankSession;
    }

    public List<Person> getPersonList() {
        return Collections.unmodifiableList(personList);
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
        if (tankSession != null) {
            return tankSession.getId();
        }
        return null;
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
                ", timestamp=" + date +
                ", tankSession=" + tankSession +
                ", personList=" + personList +
                '}';
    }
}
