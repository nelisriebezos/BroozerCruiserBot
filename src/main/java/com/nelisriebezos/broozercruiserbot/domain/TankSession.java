package com.nelisriebezos.broozercruiserbot.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TankSession {
    private Long id;
    private Timestamp timestamp;
    private Car car;
    List<Correctie> correctieList = new ArrayList<>();
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

    public List<Correctie> getCorrectieList() {
        return correctieList;
    }

    public void setCorrectieList(List<Correctie> correctieList) {
        this.correctieList = correctieList;
    }

    public boolean addCorrectie(Correctie correctie) {
        if (!correctieList.contains(correctie)) {
            correctieList.add(correctie);
            return true;
        }
        return false;
    }

    public void removeCorrectie(Correctie correctie) {
        correctieList.remove(correctie);
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
