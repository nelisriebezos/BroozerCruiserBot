package com.nelisriebezos.broozercruiserbot.application.commands;

import com.nelisriebezos.broozercruiserbot.BroozerCruiserBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class RemoveCar implements BotCommand {
    private static final Logger LOG = LoggerFactory.getLogger(RemoveCar.class);

    enum State {
        QUESTION1, EXECUTE
    }

    State state;

    public RemoveCar() {
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
                    bot.sendTextMessage(chatId, "Geef de kilometerstand van de auto");
                    state = State.EXECUTE;
                    break;
                case EXECUTE:
                    try {
                        int anserInInteger = Integer.parseInt(message);
//                        CarDAO carDAO = new CarDAO(connection);
//                        carDAO.delete(carDAO.findByKmCounter(anserInInteger).getId());
//                        connection.commit();
                    } catch (NumberFormatException e) {
                        LOG.error(e.getMessage(), e);
                        bot.sendTextMessage(chatId, "De auto is niet verwijderd");
                        break;
                    }
                    bot.sendTextMessage(chatId, "De auto is verwijderd");
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
        return (message != null && message.toLowerCase().startsWith("-removecar"));
    }
}
