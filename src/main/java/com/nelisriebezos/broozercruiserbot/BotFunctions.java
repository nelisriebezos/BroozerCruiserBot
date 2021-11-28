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

public final class BotFunctions{
    private static final Logger LOG = LoggerFactory.getLogger(BroozerCruiserBot.class);


    //input: personen en kmstand
    //output: opgetelde gereden km en opgetelde kmstand
    public static void addKmPerPerson(List<Chauffeur> chauffeurList, int mileage) {
        if (mileage < 0) throw new NegativeNumberException(mileage + ", must be positive");
        if (chauffeurList.size() < 1) throw new NoChauffeurException("ChauffeurList is empty");

        Car car = BroozerCruiserBot.getCarHibernateDAO().findById(1);
        if (mileage < car.getMileage()) throw new NegativeMileageException("Mileage input lower than last recorded mileage");

        double amountPerPerson = (double) car.caluclateAmountOfKm(mileage) / chauffeurList.size();

        for (Chauffeur chauffeur : chauffeurList) {
            chauffeur.addAmountOfKm(amountPerPerson);
            LOG.info(chauffeur.getName() + ", " + amountPerPerson + " km added");
        }
        car.addSessionKmAmount(mileage);
        car.setMileage(mileage);
    }


    public static List<String> calculateCosts(int totalCost) {
        if (totalCost < 0) throw new NegativeNumberException(totalCost + ", must be positive");

        List<Chauffeur> chauffeurList = BroozerCruiserBot.getChauffeurHibernateDAO().findAll();
        List<String> priceList = new ArrayList<>();

        for (Chauffeur chauffeur : chauffeurList) {
            double moneyOwed = totalCost * (chauffeur.getAmountOfKm() / chauffeur.getCar().getSessionKmAmount());
            chauffeur.addTotalAMountMoneySpend(moneyOwed);
            LOG.info(chauffeur.getName() + ", " + moneyOwed + " added to total amount spend");
            priceList.add(chauffeur.getName() + ": " + moneyOwed);
        }
        return priceList;
    }
}
