package com.nelisriebezos.broozercruiserbot.domain;

import com.nelisriebezos.broozercruiserbot.utils.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TankSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;

    @OneToMany(
            mappedBy = "tankSession",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Trip> tripList = new ArrayList<>();

    public TankSession(Date date, Car car) {
        this.date = date;
        this.car = car;
    }

    public int calculateTotalAmountOfDrivenKm() {
        int totalAmountOfDrivenKm = 0;
        for (Trip trip : tripList) {
            totalAmountOfDrivenKm += trip.getAmountOfKm();
        }
        return totalAmountOfDrivenKm;
    }

    public HashMap<String, Double> calculateTotalAmountOfDrivenKmPerPerson() {
        HashMap<String, Double> personKmTotalAmount = new HashMap<>();
        for (Trip trip : tripList) {
            HashMap<String, Double> tripMap = trip.calculateAmountOfDrivenKmPerPerson();
            tripMap.forEach((k, v) -> personKmTotalAmount.merge(k, v, Double::sum));
        }
        return personKmTotalAmount;
    }

    public HashMap<String, Double> calculatePricePerPerson(int gasCosts) {
        HashMap<String, Double> pricePerPerson = new HashMap<>();
        HashMap<String, Double> personKmTotalAmount = calculateTotalAmountOfDrivenKmPerPerson();
        int scale = (int) Math.pow(10, 1);
        personKmTotalAmount.forEach(
                (k, v) -> {
                    double value = (v / gasCosts) * 100;
                    pricePerPerson.put(k, (double) Math.round(value * scale) / scale);
                }
        );
        return pricePerPerson;
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
    @Generated
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TankSession)) return false;
        TankSession that = (TankSession) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    @Generated
    public int hashCode() {
        return Objects.hash(getId());
    }
}
