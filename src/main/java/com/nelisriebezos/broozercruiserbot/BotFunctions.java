package com.nelisriebezos.broozercruiserbot;

import com.nelisriebezos.broozercruiserbot.domain.Car;
import com.nelisriebezos.broozercruiserbot.domain.Chauffeur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public final class BotFunctions{
    private static final Logger LOG = LoggerFactory.getLogger(BroozerCruiserBot.class);


    //input: personen en kmstand
    //output: opgetelde gereden km en opgetelde kmstand
    public static void addKmPerPerson(List<Chauffeur> chauffeurList, int mileage) {
        Car car = chauffeurList.get(0).getCar();
        double amountPerPerson = (double) car.caluclateAmountOfKm(mileage) / chauffeurList.size();
        car.addSessionKmAmount(mileage);

        for (Chauffeur chauffeur : chauffeurList) {
            chauffeur.addAmountOfKm(amountPerPerson);
            LOG.info(chauffeur.getName() + " " + amountPerPerson + " km added");
        }
        car.setMileage(mileage);
    }

    //input: totaal prijs
    //output: lijst met tekst met hoeveel iedereen moet betalen
    public static List<String> calculateCosts(int totalCost) {

        //haal iedereen op uit database en zet in lijst:

        List<Chauffeur> chauffeurList = new ArrayList<>();

        List<String> priceList = new ArrayList<>();

        for (Chauffeur chauffeur : chauffeurList) {
            double moneyOwed = totalCost * (chauffeur.getAmountOfKm() / chauffeur.getCar().getSessionKmAmount());
            chauffeur.addTotalAMountMoneySpend(moneyOwed);
            priceList.add(chauffeur.getName() + ": " + moneyOwed);
        }
        return priceList;
    }



}
