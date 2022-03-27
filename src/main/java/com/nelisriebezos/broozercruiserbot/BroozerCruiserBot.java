package com.nelisriebezos.broozercruiserbot;

import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.domain.application.BotCommand;
import com.nelisriebezos.broozercruiserbot.domain.application.commands.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BroozerCruiserBot extends TelegramLongPollingBot {
    private static final Logger LOG = LoggerFactory.getLogger(BroozerCruiserBot.class);
    private List<BotCommand> botCommandList = new ArrayList<>();
    private BotCommand activeCommand = null;

    public BroozerCruiserBot() throws DatabaseException {
        botCommandList.add(new AddCar());
        botCommandList.add(new RemoveCar());
        botCommandList.add(new AddPerson());
        botCommandList.add(new AddTankSession());
        botCommandList.add(new AddTrip());
        botCommandList.add(new CalculateCosts());
        botCommandList.add(new CallCommandMenu());
        botCommandList.add(new CallHelpMenu());
        botCommandList.add(new RemovePerson());
        botCommandList.add(new RemoveTankSession());
        botCommandList.add(new RemoveTrip());
        botCommandList.add(new UpdateTrip());
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
                activeCommand = startConversation(chatId, message);
            }
            if (activeCommand != null)
                activeCommand = activeCommand.execute(chatId, message, this);


        } catch (TelegramApiException | SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public BotCommand startConversation(String chatId, String message) throws SQLException, TelegramApiException {
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


    public static void main(String[] args) throws Exception {

        BroozerCruiserBot bot = new BroozerCruiserBot();
        bot.startBot();
    }
}
