package com.nelisriebezos.broozercruiserbot.domain;

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

    private Date date = new Date();

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Trip> tripList = new ArrayList<>();

    public static TankSession to() {
        return new TankSession();
    }

    public int calculateDrivenKm() {
        int totalAmountOfDrivenKm = 0;
        for (Trip trip : tripList) {
            totalAmountOfDrivenKm += trip.getAmountOfKm();
        }
        return totalAmountOfDrivenKm;
    }

    public HashMap<String, Double> calculateKmPerPerson() {
        HashMap<String, Double> personKmTotalAmount = new HashMap<>();
        for (Trip trip : tripList) {
            trip.calculateKmPerPerson().forEach((k, v) -> personKmTotalAmount.merge(k, v, Double::sum));
        }
        return personKmTotalAmount;
    }

    public HashMap<String, Double> calculatePricePerPerson(int gasCosts, int amountOfKm, HashMap<String, Double> kmPerPersonMap) {
        HashMap<String, Double> pricePerPerson = new HashMap<>();
        int scale = (int) Math.pow(10, 1);
        kmPerPersonMap.forEach(
                (k, v) -> {
                    double value = v / amountOfKm * gasCosts;
                    pricePerPerson.put(k, (double) Math.round(value * scale) / scale);
                }
        );
        return pricePerPerson;
    }

    public Trip createTrip(List<Person> personList, int amountOfDrivenKm) {
        Trip trip = new Trip(amountOfDrivenKm);
        for (Person person : personList) trip.addPerson(person);
        return trip;
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
        if (!(o instanceof TankSession)) return false;
        TankSession that = (TankSession) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
