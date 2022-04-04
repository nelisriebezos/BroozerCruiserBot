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
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int amountOfKm = 0;
    private Date date = new Date();

    @ManyToMany(mappedBy = "tripList")
    private List<Person> personList = new ArrayList<>();

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
        for (Person person : personList) {
            personKmAmount.put(person.getName(), (double) Math.round(toScale * scale) / scale);
        }
        return personKmAmount;
    }

    public boolean addPerson(Person person) {
        if (!personList.contains(person)) {
            personList.add(person);
            return true;
        }
        return false;
    }

    public void removePerson(Person person) {
        personList.remove(person);
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
