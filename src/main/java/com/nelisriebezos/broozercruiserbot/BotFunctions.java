package com.nelisriebezos.broozercruiserbot;

import com.nelisriebezos.broozercruiserbot.Exceptions.NegativeMileageException;
import com.nelisriebezos.broozercruiserbot.Exceptions.NegativeNumberException;
import com.nelisriebezos.broozercruiserbot.Exceptions.NoChauffeurException;
import com.nelisriebezos.broozercruiserbot.domain.Car;
import com.nelisriebezos.broozercruiserbot.domain.Chauffeur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class BotFunctions{
    private static final Logger LOG = LoggerFactory.getLogger(BroozerCruiserBot.class);

//    public static void addKmPerPerson(List<Chauffeur> chauffeurList, int mileage, Car car) {
//        if (mileage < 0) throw new NegativeNumberException(mileage + ", must be positive");
//        if (chauffeurList.size() < 1) throw new NoChauffeurException("ChauffeurList is empty");
//        if (mileage < car.getMileage()) throw new NegativeMileageException("Mileage input lower than last recorded mileage");
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
