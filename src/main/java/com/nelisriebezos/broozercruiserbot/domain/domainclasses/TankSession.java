package com.nelisriebezos.broozercruiserbot.domain.domainclasses;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TankSession {
    private Long id;
    private Timestamp timestamp;
    private Car car;
    List<Correction> correctionList = new ArrayList<>();
    List<Trip> tripList = new ArrayList<>();

    public TankSession() {
    }

    public TankSession(Long id) {
        this.id = id;
    }

    public TankSession(Long id, Timestamp timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public TankSession(Timestamp timestamp, Car car) {
        this.timestamp = timestamp;
        this.car = car;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Correction> getCorrectieList() {
        return correctionList;
    }

    public void setCorrectieList(List<Correction> correctionList) {
        this.correctionList = correctionList;
    }

    public boolean addCorrectie(Correction correction) {
        if (!correctionList.contains(correction)) {
            correctionList.add(correction);
            return true;
        }
        return false;
    }

    public void removeCorrectie(Correction correction) {
        correctionList.remove(correction);
    }

    public List<Trip> getTripList() {
        return tripList;
    }

    public void setTripList(List<Trip> tripList) {
        this.tripList = tripList;
    }

    public boolean addTrip(Trip trip) {
        if (!tripList.contains(trip)) {
            tripList.add(trip);
            return true;
        }
        return false;
    }

    public void removeTrip(Trip trip) {
        tripList.remove(trip);
    }

    public Long getCarId() {
        return car.getId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TankSession)) return false;
        TankSession that = (TankSession) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getTimestamp(), that.getTimestamp()) && Objects.equals(getCar(), that.getCar()) && Objects.equals(correctionList, that.correctionList) && Objects.equals(getTripList(), that.getTripList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTimestamp(), getCar(), correctionList, getTripList());
    }

    @Override
    public String toString() {
        return "TankSession{" +
                "id=" + id +
                ", timestamp=" + timestamp +
                ", car=" + car +
                ", correctionList=" + correctionList +
                ", tripList=" + tripList +
                '}';
    }
}
