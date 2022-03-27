package com.nelisriebezos.broozercruiserbot.domain.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter @Setter @ToString @NoArgsConstructor
public class Trip {
    private Long id;
    private int mileageInKm;
    private Date date;
    private TankSession tankSession;
    List<Person> personList = new ArrayList<>();

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
