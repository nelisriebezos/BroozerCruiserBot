package com.nelisriebezos.broozercruiserbot.domain.service.commands;

import com.nelisriebezos.broozercruiserbot.BroozerCruiserBot;
import com.nelisriebezos.broozercruiserbot.domain.service.BotCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Connection;

public class RemoveTankSession implements BotCommand {
    private static final Logger LOG = LoggerFactory.getLogger(RemoveTankSession.class);

    public RemoveTankSession() {
    }

    @Override
    public void reset() {

    }

    @Override
    public BotCommand execute(String chatId, String message, Connection connection, BroozerCruiserBot bot) throws TelegramApiException {
        return null;
    }

    @Override
    public boolean match(String message) {
        return false;
    }
}