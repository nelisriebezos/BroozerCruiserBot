package com.nelisriebezos.broozercruiserbot.domain;

public class Chauffeur {
    private String name;
    private double amountOfKm;
    private Car car;

    private double totalAmountOfKm;
    private double totalAmountMoneySpend;

    public Chauffeur(String name) {
        this.name = name;
    }

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
