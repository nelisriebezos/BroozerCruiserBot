package com.nelisriebezos.broozercruiserbot.application.commands;

import com.nelisriebezos.broozercruiserbot.BroozerCruiserBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CallCommandMenuCommand implements BotCommand {
    private static final Logger LOG = LoggerFactory.getLogger(CallCommandMenuCommand.class);
    private final String commandMessage = "Alle commands: \n -newtank\n -newtrip\n -calculate\n ";
    public CallCommandMenuCommand() {
    }

    @Override
    public void reset() {
    }

    @Override
    public BotCommand execute(String chatId, String message, BroozerCruiserBot bot) throws TelegramApiException {
        try {
            bot.sendTextMessage(chatId, commandMessage);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            bot.sendTextMessage(chatId, "Er ging iets fout");
        }
        return null;
    }

    @Override
    public boolean match(String message) {
        return false;
    }
}
