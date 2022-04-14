package com.nelisriebezos.broozercruiserbot.application.commands;

import com.nelisriebezos.broozercruiserbot.BroozerCruiserBot;
import com.nelisriebezos.broozercruiserbot.application.CarService;
import com.nelisriebezos.broozercruiserbot.application.TripService;
import com.nelisriebezos.broozercruiserbot.domain.Car;
import com.nelisriebezos.broozercruiserbot.domain.TankSession;
import com.nelisriebezos.broozercruiserbot.domain.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class AddTripCommand implements BotCommand {
    private static final Logger LOG = LoggerFactory.getLogger(AddTripCommand.class);
    private final TripService tripService;
    private final CarService carService;
    enum State {QUESTION1, QUESTION2, EXCECUTE}
    State state;

    public AddTripCommand(TripService tripService, CarService carService) {
        this.tripService = tripService;
        this.carService = carService;
    }

    @Override
    public void reset() {
        state = State.QUESTION1;
    }

    @Override
    public BotCommand execute(String chatId, String message, BroozerCruiserBot bot) throws TelegramApiException {
        BotCommand result = this;
        Trip trip = null;
        try {
            switch (state) {
                case QUESTION1:
                    bot.sendTextMessage(chatId, "Wat is de kilometerstand van de auto?");
                    state = State.QUESTION2;
                    break;
                case QUESTION2:
                    try {
                        int answerInInteger = Integer.parseInt(message);
                        trip = Trip.builder()
                                .tankSession(carService.getCar(bot.getActiveCarId()).getCurrentTanksession())
                                .amountOfKm(answerInInteger)
                                .build();
                        bot.sendTextMessage(chatId, "Wie zaten er in de trip? (alle namen in een bericht met spatie ertussen)");
                        break;
                    } catch (NumberFormatException e) {
                        LOG.error(e.getMessage(), e);
                        bot.sendTextMessage(chatId, "Het antwoord moet alleen nummers bevatten, vul kmstand in");
                    }
                case EXCECUTE:
                    String[] names = message.split(" ");
                    for (String name : names) {
                        trip.addPersonName(name);
                    }
                    tripService.persistTrip(trip);
                    bot.sendTextMessage(chatId, "De trip is geregistreerd");
                    this.reset();
                    result = null;
                    break;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            bot.sendTextMessage(chatId, "Er ging iets fout");
        }
        return result;
    }

    @Override
    public boolean match(String message) {
        return (message != null && message.toLowerCase().startsWith("-trip"));
    }
}
