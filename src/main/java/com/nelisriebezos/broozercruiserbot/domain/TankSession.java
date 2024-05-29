package com.nelisriebezos.broozercruiserbot.domain;

import lombok.*;

import java.util.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TankSession {
    private Long id;
    private Date date = new Date();
    @ToString.Exclude
    @Builder.Default
    private List<Trip> tripList = new ArrayList<>();
    private Car car;

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

    public Trip createTrip(List<String> personList, int amountOfDrivenKm) {
        Trip trip = new Trip(amountOfDrivenKm);
        for (String personName : personList) trip.addPersonName(personName);
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
