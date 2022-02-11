package com.nelisriebezos.broozercruiserbot.domain.service.commands;

import com.nelisriebezos.broozercruiserbot.BroozerCruiserBot;
import com.nelisriebezos.broozercruiserbot.domain.domainclasses.Car;
import com.nelisriebezos.broozercruiserbot.domain.service.BotCommand;
import com.nelisriebezos.broozercruiserbot.domain.service.dao.CarDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Connection;

public class AddCar implements BotCommand {
    private static final Logger LOG = LoggerFactory.getLogger(AddCar.class);
    enum State {
        QUESTION1, EXCECUTE
    }

    State state;

    public AddCar() {
    }

    @Override
    public void reset() {
        state = State.QUESTION1;
    }

    @Override
    public BotCommand execute(String chatId, String message, Connection connection, BroozerCruiserBot bot) throws TelegramApiException {

        BotCommand result = this;
        try {
            switch (state) {
                case QUESTION1:
                    bot.sendTextMessage(chatId, "Wat is de kilometerstand van de auto?");
                    state = State.EXCECUTE;
                    break;
                case EXCECUTE:
                    System.out.println("test buiten try block");
                    System.out.println(message);
                    try {
                        System.out.println("test try block");
                        int answerInInteger = Integer.parseInt(message);
                        Car createdCar = new Car(answerInInteger);
                        CarDAO carDAO = new CarDAO(connection);
                        carDAO.create(createdCar);
                    } catch (NumberFormatException ex) {
                        LOG.error(ex.getMessage(), ex);
                        bot.sendTextMessage(chatId, "Het antwoord moet alleen nummers bevatten");
                        break;
                }
                    bot.sendTextMessage(chatId, "De auto is aangemaakt");
                    this.reset();
                    result = null;
                    break;
            }

        } catch (Exception e) {
            bot.sendTextMessage(chatId, "Er ging iets fout");

        }
        return result;
    }

    @Override
    public boolean match(String message) {
        return (message != null && message.toLowerCase().startsWith("-car"));
    }
}
