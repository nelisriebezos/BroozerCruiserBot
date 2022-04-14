package com.nelisriebezos.broozercruiserbot.application.commands;

import com.nelisriebezos.broozercruiserbot.BroozerCruiserBot;
import com.nelisriebezos.broozercruiserbot.application.CarService;
import com.nelisriebezos.broozercruiserbot.domain.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class AddCarCommand implements BotCommand {
    private static final Logger LOG = LoggerFactory.getLogger(AddCarCommand.class);
    private final CarService carService;
    enum State {QUESTION1, EXCECUTE}
    State state;

    public AddCarCommand(CarService carService) {
        this.carService = carService;
    }

    @Override
    public void reset() {
        state = State.QUESTION1;
    }

    @Override
    public BotCommand execute(String chatId, String message, BroozerCruiserBot bot) throws TelegramApiException {
        BotCommand result = this;
        try {
            switch (state) {
                case QUESTION1:
                    bot.sendTextMessage(chatId, "Wat is de kilometerstand van de auto?");
                    state = State.EXCECUTE;
                    break;
                case EXCECUTE:
                    try {
                        int answerInInteger = Integer.parseInt(message);
                        Car createdCar = Car.builder()
                                .kmCounter(answerInInteger)
                                .build();
                        carService.persistCar(createdCar);
                    } catch (NumberFormatException ex) {
                        LOG.error(ex.getMessage(), ex);
                        bot.sendTextMessage(chatId, "Het antwoord moet alleen nummers bevatten, vul kmstand in");
                        break;
                    }
                    bot.sendTextMessage(chatId, "De auto is aangemaakt");
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
        return (message != null && message.toLowerCase().startsWith("-addcar"));
    }
}
