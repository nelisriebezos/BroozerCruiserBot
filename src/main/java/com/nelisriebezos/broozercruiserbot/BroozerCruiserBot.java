package com.nelisriebezos.broozercruiserbot;

import com.nelisriebezos.broozercruiserbot.application.CarService;
import com.nelisriebezos.broozercruiserbot.application.TankSessionService;
import com.nelisriebezos.broozercruiserbot.application.TripService;
import com.nelisriebezos.broozercruiserbot.application.commands.BotCommand;
import com.nelisriebezos.broozercruiserbot.application.commands.*;
import com.nelisriebezos.broozercruiserbot.utils.Safe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BroozerCruiserBot extends TelegramLongPollingBot {
    private static final Logger LOG = LoggerFactory.getLogger(BroozerCruiserBot.class);
    private List<BotCommand> botCommandList = new ArrayList<>();
    private BotCommand activeCommand = null;
    private Long activeCarId = null;

    public BroozerCruiserBot(CarService carService, TankSessionService tankSessionService, TripService tripService) {
        botCommandList.add(new AddCarCommand(carService));
        botCommandList.add(new SetCurrentCarCommand(carService));
        botCommandList.add(new RemoveCarCommand(carService));
        botCommandList.add(new AddTankSessionCommand(tankSessionService, carService));
        botCommandList.add(new AddTripCommand(tripService, carService));
        botCommandList.add(new CalculateCostsCommand(carService, tankSessionService));
//        botCommandList.add(new CallCommandMenuCommand());
//        botCommandList.add(new CallHelpMenuCommand());
        botCommandList.add(new RemoveTankSessionCommand(tankSessionService));
        botCommandList.add(new RemoveTripCommand(tripService));
        botCommandList.add(new UpdateTripCommand(tripService));
    }

    public void setActiveCarId(Long activeCarId) {
        this.activeCarId = activeCarId;
    }

    public Long getActiveCarId() {
        return activeCarId;
    }

    @Override
    public String getBotUsername() {
        return this.getClass().getName();
    }

    @Override
    public String getBotToken() {
        return Safe.getSecrets().getTelegramBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            String message = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();
            message = message.toLowerCase();

            if (activeCommand == null) {
                activeCommand = startConversation(message);
            }
            if (activeCommand != null)
                activeCommand = activeCommand.execute(chatId, message, this);


        } catch (TelegramApiException | SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public BotCommand startConversation(String message) throws SQLException, TelegramApiException {
        for (BotCommand cmd : botCommandList) {
            if (cmd.match(message)) {
                cmd.reset();
                return cmd;
            }
        }
        return null;
    }

    public void sendTextMessage(String chatId, String message) throws TelegramApiException {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.setChatId(chatId);
        execute(sendMessage);
        LOG.info(message);
    }

    public void startBot() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(this);
        LOG.info("Bot started");
    }
}
