package com.nelisriebezos.broozercruiserbot.domain.domainclasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private Long id;
    private String name;
    List<Trip> tripList = new ArrayList<>();

    public Person() {
    }

    public Person(Long id) {
        this.id = id;
    }

    public Person(Long id, String name) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getId(), person.getId()) && Objects.equals(getName(), person.getName()) && Objects.equals(getTripList(), person.getTripList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getTripList());
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tripList=" + tripList +
                '}';
    }
}
