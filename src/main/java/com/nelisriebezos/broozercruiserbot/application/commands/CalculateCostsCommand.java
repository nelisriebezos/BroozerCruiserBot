package com.nelisriebezos.broozercruiserbot.application.commands;

import com.nelisriebezos.broozercruiserbot.BroozerCruiserBot;
import com.nelisriebezos.broozercruiserbot.application.CarService;
import com.nelisriebezos.broozercruiserbot.application.TankSessionService;
import com.nelisriebezos.broozercruiserbot.domain.Car;
import com.nelisriebezos.broozercruiserbot.domain.TankSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;

public class CalculateCostsCommand implements BotCommand {
    private static final Logger LOG = LoggerFactory.getLogger(CalculateCostsCommand.class);
    private final CarService carService;
    private final TankSessionService tankSessionService;
    enum State {QUESTION1, EXCECUTE}
    State state;

    public CalculateCostsCommand(CarService carService, TankSessionService tankSessionService) {
        this.carService = carService;
        this.tankSessionService = tankSessionService;
    }

    @Override
    public void reset() {}

    @Override
    public BotCommand execute(String chatId, String message, BroozerCruiserBot bot) throws TelegramApiException {
        BotCommand result = this;
        try {
            switch (state) {
                case QUESTION1:
                    bot.sendTextMessage(chatId, "Wat was de prijs van tanken?");
                    break;
                case EXCECUTE:
                    try {
                        int answer = Integer.parseInt(message);
                        Car car = carService.getCar(bot.getActiveCarId());
                        TankSession tankSession = car.getCurrentTanksession();
                        HashMap<String, Double> pricePerPerson = tankSession.calculatePricePerPerson(answer, tankSession.calculateDrivenKm(), tankSession.calculateKmPerPerson());
                        StringBuilder response = new StringBuilder("Prijsoverzicht: " + "\n");
                        for (int i = 0; i < pricePerPerson.size(); i++) {
                            String personName = (String) pricePerPerson.keySet().toArray()[i];
                            response.append(personName).append(": ").append(pricePerPerson.get(personName));
                        }
                        bot.sendTextMessage(chatId, response.toString());
                        this.reset();
                        result = null;
                        break;
                    } catch (NumberFormatException e) {
                        LOG.error(e.getMessage(), e);
                        bot.sendTextMessage(chatId, "Het antwoord moet alleen nummers bevatten, vul kmstand in");
                    }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            bot.sendTextMessage(chatId, "Er ging iets mis");
        }
        return result;
    }

    @Override
    public boolean match(String message) {
        return (message != null && message.toLowerCase().startsWith("-calculate"));
    }
}
