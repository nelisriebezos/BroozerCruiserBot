package com.nelisriebezos.broozercruiserbot.domain;

import javax.persistence.*;

@Entity
@Table(name = "chauffeur")
public class Chauffeur {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;

    @ManyToOne
    private Rit rit;

    private double totalAmountOfKm;
    private double totalAmountMoneySpend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chauffeur() {}

//    public void addAmountOfKm(double amountOfKm) {
//        this.amountOfKm += amountOfKm;
//        this.totalAmountOfKm += amountOfKm;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public double getAmountOfKm() {
//        return amountOfKm;
//    }
//
//    public void setAmountOfKm(double amountOfKm) {
//        this.amountOfKm = amountOfKm;
//    }

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

    public String getChauffeurData() {
        return name + ": \n" +
                "Totaal km: " + totalAmountOfKm + "\n" +
                "Totaal money spend: " + totalAmountMoneySpend;
    }

    @Override
    public String toString() {
        return "Chauffeur{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", amountOfKm=" + amountOfKm +
                ", totalAmountOfKm=" + totalAmountOfKm +
                ", totalAmountMoneySpend=" + totalAmountMoneySpend +
                '}';
    }
}
