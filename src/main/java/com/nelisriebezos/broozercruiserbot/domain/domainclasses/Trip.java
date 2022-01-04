package com.nelisriebezos.broozercruiserbot.domain.domainclasses;


import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Trip {
    private Long id;
    private int mileageInKm;
    private Date date;
    private TankSession tankSession;
    List<Person> personList = new ArrayList<>();

    public Trip() {
    }

    public Trip(int mileageInKm, Date date, TankSession tankSession, List<Person> personList) {
        this.mileageInKm = mileageInKm;
        this.date = date;
        this.tankSession = tankSession;
        this.personList = personList;
    }

    public Trip(long id, int mileageInKm, Date date) {
        this.id = id;
        this.mileageInKm = mileageInKm;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMileageInKm() {
        return mileageInKm;
    }

    public void setMileageInKm(int mileageInKm) {
        this.mileageInKm = mileageInKm;
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

    public void clearPersonsList() {
        personList.clear();
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
                ", distance=" + mileageInKm +
                ", timestamp=" + date +
                ", tankSession=" + tankSession +
                ", personList=" + personList +
                '}';
    }
}
