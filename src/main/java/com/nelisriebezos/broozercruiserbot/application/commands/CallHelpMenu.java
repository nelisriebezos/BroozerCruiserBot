package com.nelisriebezos.broozercruiserbot.application.commands;

import com.nelisriebezos.broozercruiserbot.BroozerCruiserBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CallHelpMenu implements BotCommand {
    private static final Logger LOG = LoggerFactory.getLogger(CallHelpMenu.class);

    public CallHelpMenu() {
    }

    @Override
    public void reset() {

    }

    @Override
    public BotCommand execute(String chatId, String message, BroozerCruiserBot bot) throws TelegramApiException {
        return null;
    }

    @Override
    public boolean match(String message) {
        return false;
    }
}
