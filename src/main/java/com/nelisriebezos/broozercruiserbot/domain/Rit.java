package com.nelisriebezos.broozercruiserbot.domain;

import org.checkerframework.checker.units.qual.C;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rit")
public class Rit {
    @Id
    @Column(name = "id")
    private Long id;
    @OneToMany()
    private List<Chauffeur> chauffeurList;
    @ManyToOne
    private Car car;
    private int amountOfKm;

    public Rit(List<Chauffeur> chauffeurList, Car car) {
        this.chauffeurList = chauffeurList;
        this.car = car;
    }

    public Rit() {
    }

    public List<Chauffeur> getChauffeurList() {
        return chauffeurList;
    }

    public void setChauffeurList(List<Chauffeur> chauffeurList) {
        this.chauffeurList = chauffeurList;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getAmountOfKm() {
        return amountOfKm;
    }

    public void setAmountOfKm(int amountOfKm) {
        this.amountOfKm = amountOfKm;
    }
}
