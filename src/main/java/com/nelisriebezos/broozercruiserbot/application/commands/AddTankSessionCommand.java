package com.nelisriebezos.broozercruiserbot.application.commands;

import com.nelisriebezos.broozercruiserbot.BroozerCruiserBot;
import com.nelisriebezos.broozercruiserbot.application.TankSessionService;
import com.nelisriebezos.broozercruiserbot.domain.TankSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class AddTankSessionCommand implements BotCommand {
    private static final Logger LOG = LoggerFactory.getLogger(AddTankSessionCommand.class);
    private final TankSessionService tankSessionService;

    public AddTankSessionCommand(TankSessionService tankSessionService) {
        this.tankSessionService = tankSessionService;
    }

    @Override
    public void reset() {}

    @Override
    public BotCommand execute(String chatId, String message, BroozerCruiserBot bot) throws TelegramApiException {
        BotCommand result = this;
        try {
            TankSession tankSession = TankSession.builder().build();
            tankSessionService.persistTankSession(tankSession);
            result = null;
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            bot.sendTextMessage(chatId, "Er ging iets fout");
        }
        return result;
    }

    @Override
    public boolean match(String message) {
        return (message != null && message.toLowerCase().startsWith("-newtank"));
    }
}
