package com.nelisriebezos.broozercruiserbot.domain.service.commands;

import com.nelisriebezos.broozercruiserbot.BroozerCruiserBot;
import com.nelisriebezos.broozercruiserbot.domain.service.BotCommand;
import com.nelisriebezos.broozercruiserbot.domain.service.dao.CarDAO;

import java.sql.Connection;
import java.util.Locale;

public class AddCar implements BotCommand {
    enum State {
        VRAAG1, VRAAG2, VRAAG3
    }

    int counter = 0;
    State state;

    public AddCar() {
    }

    @Override
    public void reset() {
        counter = 0;
        state = State.VRAAG1;
    }

    @Override
    public BotCommand execute(String chatId, String message, Connection connection, BroozerCruiserBot bot) {

        BotCommand result = this;
        try {
            switch (state) {
                case VRAAG1:
                    bot.sendTextMessage(chatId, "STEL VRAAG 1 =" + message);
                    state = State.VRAAG2;
                    break;
                case VRAAG2:
                    // verwerk antwoord 1 in message
                    bot.sendTextMessage(chatId, "STEL VRAAG 2 =" + message);
                    state = State.VRAAG3;
                    break;
                case VRAAG3:
                    // verwerk antwoord 2 in message
                    // doe je ding

                    result = null; //einde discussie
                    break;
            }

        } catch (Exception e) {
            // LOG
        }
        return result;
    }

    @Override
    public boolean match(String message) {
        return (message != null && message.toLowerCase().startsWith("car"));
    }
}
