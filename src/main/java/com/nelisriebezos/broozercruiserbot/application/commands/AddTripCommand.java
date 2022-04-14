package com.nelisriebezos.broozercruiserbot.application.commands;

import com.nelisriebezos.broozercruiserbot.BroozerCruiserBot;
import com.nelisriebezos.broozercruiserbot.application.TripService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class AddTripCommand implements BotCommand {
    private static final Logger LOG = LoggerFactory.getLogger(AddTripCommand.class);
    private final TripService tripService;
    enum State {QUESTION1, EXCECUTE}
    State state;

    public AddTripCommand(TripService tripService) {
        this.tripService = tripService;
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
                        int answerInInteger = Integer
                    }
            }
        }
        return result;
    }

    @Override
    public boolean match(String message) {
        return (message != null && message.toLowerCase().startsWith("-trip"));
    }
}
