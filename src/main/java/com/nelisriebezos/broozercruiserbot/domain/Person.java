package com.nelisriebezos.broozercruiserbot.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany
    private List<Trip> tripList = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public static Person to(String name) {
        return new Person(name);
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
        return Objects.equals(getId(), person.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
