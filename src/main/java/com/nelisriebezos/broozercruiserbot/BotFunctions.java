package com.nelisriebezos.broozercruiserbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotFunctions{
    private static final Logger LOG = LoggerFactory.getLogger(BotFunctions.class);

//    public static void addKmPerPerson(List<Chauffeur> chauffeurList, int mileage, Car car) {
//        if (mileage < 0) throw new NegativeNumberException(mileage + ", must be positive");
//        if (chauffeurList.isEmpty()) throw new NoChauffeurException("ChauffeurList is empty");
//        if (mileage < car.getMileage()) throw new NegativeDistanceException("Mileage input lower than last recorded mileage");
//
//        double amountPerPerson = (double) car.calculateAmountOfKm(mileage) / chauffeurList.size();
//        for (Chauffeur chauffeur : chauffeurList) chauffeur.addAmountOfKm(amountPerPerson);
//
//        car.addSessionKmAmount(mileage);
//        car.setMileage(mileage);
//    }
//
//
//    public static List<String> calculateCosts(int totalCost, List<Chauffeur> chauffeurList, Car car) {
//        if (totalCost < 0) throw new NegativeNumberException(totalCost + ", must be positive");
//        List<String> priceList = new ArrayList<>();
//
//        for (Chauffeur chauffeur : chauffeurList) {
//            double moneyOwed = totalCost * (chauffeur.getAmountOfKm() / car.getSessionKmAmount());
//            chauffeur.addTotalAMountMoneySpend(moneyOwed);
//            priceList.add(chauffeur.getName() + ": " + moneyOwed);
//        }
//        return priceList;
//    }
}
