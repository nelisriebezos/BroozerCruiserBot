package com.nelisriebezos.broozercruiserbot.domain;

import javax.persistence.*;

@Entity
@Table(name = "chauffeur")
public class Chauffeur {
    @Id
    @Column(name = "chauffeur_id", nullable = false)
    private Long id;
    private String name;
    private double amountOfKm;
    @OneToOne(mappedBy = "id")
    private Car car;

    private double totalAmountOfKm;
    private double totalAmountMoneySpend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chauffeur() {}

    public void addAmountOfKm(double amountOfKm) {
        this.amountOfKm += amountOfKm;
        this.totalAmountOfKm += amountOfKm;
    }

    public void resetMileage() {
        this.amountOfKm = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmountOfKm() {
        return amountOfKm;
    }

    public void setAmountOfKm(double amountOfKm) {
        this.amountOfKm = amountOfKm;
    }

    public double getTotalAmountOfKm() {
        return totalAmountOfKm;
    }

    public void setTotalAmountOfKm(double totalAmountOfKm) {
        this.totalAmountOfKm = totalAmountOfKm;
    }

    public double getTotalAmountMoneySpend() {
        return totalAmountMoneySpend;
    }

    public void addTotalAMountMoneySpend(double amount) {
        this.totalAmountMoneySpend += amount;
    }

    public void setTotalAmountMoneySpend(double totalAmountMoneySpend) {
        this.totalAmountMoneySpend = totalAmountMoneySpend;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getChauffeurData() {
        return name + ": \n" +
                "Totaal km: " + totalAmountOfKm + "\n" +
                "Totaal money spend: " + totalAmountMoneySpend;
    }

    @Override
    public String toString() {
        return name + "\n" +
                "amountOfKm: " + amountOfKm;
    }
}
