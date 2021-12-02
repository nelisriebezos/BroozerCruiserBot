package com.nelisriebezos.broozercruiserbot.domain;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private Long id;
    private String name;
    List<Trip> tripList = new ArrayList<>();

    public Person() {
    }

    public Person(Long id) {
        this.id = id;
    }

    public Person(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
