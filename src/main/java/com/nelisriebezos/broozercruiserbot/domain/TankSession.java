package com.nelisriebezos.broozercruiserbot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter @Setter @ToString @NoArgsConstructor
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
