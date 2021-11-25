package domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;

public class Chauffeur {
    private String name;
    private double amountOfKm;
    private double moneyOwed;
    private Tank tank;

    private double totalAmountOfKm;
    private double totalAmountMoneySpend;

    public Chauffeur(String name) {
        this.name = name;
    }

    public double calculateCosts() {
        moneyOwed = Math.round(tank.getAmountOfKm() * (amountOfKm / tank.getAmountOfKm()));
        totalAmountOfKm += moneyOwed;
        return moneyOwed;
    }

    public void addAmountOfKm(double amountOfKm) {
        this.amountOfKm += amountOfKm;
        this.totalAmountOfKm += amountOfKm;
    }

    public void resetMileage() {
        this.amountOfKm = 0.0;
        this.moneyOwed = 0.0;
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

    public double getMoneyOwed() {
        return moneyOwed;
    }

    public void setMoneyOwed(double moneyOwed) {
        this.moneyOwed = moneyOwed;
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
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
        return name + "\n" +
                "amountOfKm: " + amountOfKm + "\n" +
                "moneyOwed: " + moneyOwed;
    }
}
