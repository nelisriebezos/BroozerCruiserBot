package com.nelisriebezos.broozercruiserbot.application.commands;

import com.nelisriebezos.broozercruiserbot.BroozerCruiserBot;
import com.nelisriebezos.broozercruiserbot.application.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SetCurrentCarCommand implements BotCommand {
    private static final Logger LOG = LoggerFactory.getLogger(SetCurrentCarCommand.class);
    private final CarService carService;
    enum State {QUESTION1, EXCECUTE}
    State state;

    public SetCurrentCarCommand(CarService carService) {
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
                    bot.sendTextMessage(chatId, "Wat is de naam van de auto?");
                    state = State.EXCECUTE;
                    break;
                case EXCECUTE:
                    String answer = message.toLowerCase();
                    Long carId = carService.getCarIdByName(answer);
                    bot.setActiveCarId(carId);
                    bot.sendTextMessage(chatId, "De " + answer + " is gezet als auto");
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
        return (message != null && message.toLowerCase().startsWith("-setcar"));
    }
}
