package com.nelisriebezos.broozercruiserbot.domain.domainclasses;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class TankSession {
    private Long id;
    private Date date;
    private Car car;
    List<Correction> correctionList = new ArrayList<>();
    List<Trip> tripList = new ArrayList<>();

    public TankSession() {
    }

    public TankSession(Long id, Date date) {
        this.id = id;
        this.date = date;
    }

    public TankSession(Date date, Car car) {
        this.date = date;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Correction> getCorrectieList() {
        return Collections.unmodifiableList(correctionList);
    }

    public boolean addCorrectie(Correction correction) {
        if (!correctionList.contains(correction)) {
            correctionList.add(correction);
            return true;
        }
        return false;
    }

    public void removeCorrection(Correction correction) {
        correctionList.remove(correction);
    }

    public List<Trip> getTripList() {
        return Collections.unmodifiableList(tripList);
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
        if (car != null) {
            return car.getId();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TankSession)) return false;
        TankSession that = (TankSession) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "TankSession{" +
                "id=" + id +
                ", timestamp=" + date +
                ", car=" + car +
                ", correctionList=" + correctionList +
                ", tripList=" + tripList +
                '}';
    }
}
