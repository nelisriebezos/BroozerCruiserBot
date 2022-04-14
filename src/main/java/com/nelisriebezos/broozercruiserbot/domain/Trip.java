package com.nelisriebezos.broozercruiserbot.domain;


import lombok.*;

import java.util.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trip {
    private Long id;
    private int amountOfKm = 0;
    private Date date = new Date();
    @Builder.Default
    private List<String> personList = new ArrayList<>();
    private TankSession tankSession;

    public Trip(int amountOfKm) {
        this.amountOfKm = amountOfKm;
    }

    public static Trip to(int amountOfKm) {
        return new Trip(amountOfKm);
    }

    public HashMap<String, Double> calculateKmPerPerson() {
        HashMap<String, Double> personKmAmount = new HashMap<>();
        int scale = (int) Math.pow(10, 1);
        double toScale = (float) amountOfKm / (float) personList.size();
        for (String personName : personList) {
            personKmAmount.put(personName, (double) Math.round(toScale * scale) / scale);
        }
        return personKmAmount;
    }

    public boolean addPersonName(String personName) {
        if (!personList.contains(personName)) {
            personList.add(personName);
            return true;
        }
        return false;
    }

    public void removePerson(String personName) {
        personList.remove(personName);
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

}
