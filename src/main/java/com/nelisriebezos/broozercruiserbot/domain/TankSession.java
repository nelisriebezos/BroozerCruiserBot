package com.nelisriebezos.broozercruiserbot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tanksession")
public class TankSession {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private int amountOfKm;

    public TankSession() {
    }

    public void addAmountOfKm(double amountOfKm) {
        this.amountOfKm += amountOfKm;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmountOfKm() {
        return amountOfKm;
    }

    public void setAmountOfKm(int amountOfKm) {
        this.amountOfKm = amountOfKm;
    }

    public void addAmountOfKm(int amountOfKm) {
        this.amountOfKm += amountOfKm;
    }

    @Override
    public String toString() {
        return "TankSession{" +
                "id=" + id +
                ", amountOfKm=" + amountOfKm +
                '}';
    }
}
