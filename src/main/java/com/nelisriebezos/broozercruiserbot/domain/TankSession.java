package com.nelisriebezos.broozercruiserbot.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
}
