package com.nelisriebezos.broozercruiserbot.application.commands;

import com.nelisriebezos.broozercruiserbot.BroozerCruiserBot;
import com.nelisriebezos.broozercruiserbot.application.CarService;
import com.nelisriebezos.broozercruiserbot.application.TripService;
import com.nelisriebezos.broozercruiserbot.domain.Car;
import com.nelisriebezos.broozercruiserbot.domain.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class AddTripCommand implements BotCommand {
    private static final Logger LOG = LoggerFactory.getLogger(AddTripCommand.class);
    private final TripService tripService;
    private final CarService carService;
    enum State {QUESTION1, MILEAGECHECK1, MILEAGECHECK2, QUESTION3, EXCECUTE}
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
        Car car = carService.getCar(bot.getActiveCarId());
        int answerInInteger = 0;
        try {
            switch (state) {
                case QUESTION1:
                    bot.sendTextMessage(chatId, "Wat is de kilometerstand van de auto?");
                    state = State.MILEAGECHECK1;
                    break;
                case MILEAGECHECK1:
                    try {
                        answerInInteger = Integer.parseInt(message);
                        if (car.checkDifferenceAboveHundred(answerInInteger)) {
                            bot.sendTextMessage(chatId, "Het verschil in kmstand is meer dan 100, klopt dat? ja/nee" + "\nvorige kmstand was: " + car.getKmCounter());
                            state = State.MILEAGECHECK2;
                        } else {
                            state = State.QUESTION3;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        LOG.error(e.getMessage(), e);
                        bot.sendTextMessage(chatId, "Het antwoord moet alleen nummers bevatten, vul kmstand in");
                    }
                case MILEAGECHECK2:
                    String answer = message.toLowerCase();
                    if (answer.equals("ja")) {
                        state = State.QUESTION3;
                        break;
                    } else if (answer.equals("nee")) {
                        state = State.QUESTION1;
                        break;
                    } else {
                        bot.sendTextMessage(chatId, "antwoord met ja of nee");
                        break;
                    }
                case QUESTION3:
                    trip = Trip.builder()
                            .tankSession(car.getCurrentTanksession())
                            .amountOfKm(answerInInteger)
                            .build();
                    bot.sendTextMessage(chatId, "Wie zaten er in de trip? (alle namen in een bericht met spatie ertussen)");
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
