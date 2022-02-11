package com.nelisriebezos.broozercruiserbot;

import com.nelisriebezos.broozercruiserbot.Exceptions.DatabaseException;
import com.nelisriebezos.broozercruiserbot.domain.service.BotCommand;
import com.nelisriebezos.broozercruiserbot.domain.service.commands.AddCar;
import com.nelisriebezos.broozercruiserbot.domain.service.commands.RemoveCar;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserDB;
import com.nelisriebezos.broozercruiserbot.persistence.CruiserEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BroozerCruiserBot extends TelegramLongPollingBot {
    private static final Logger LOG = LoggerFactory.getLogger(BroozerCruiserBot.class);
    private List<BotCommand> botCommandList = new ArrayList<>();
    private CruiserDB cruiserDB;
    private BotCommand activeCommand = null;

    public BroozerCruiserBot() throws DatabaseException {
        this.cruiserDB = CruiserEnvironment.getEnvironment().getCruiserDB();
        botCommandList.add(new AddCar());
        botCommandList.add(new RemoveCar());
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

                try (Connection connection = cruiserDB.getConnection()) {

                    if (activeCommand == null) {
                        activeCommand = startConversation(chatId, message);
                    }
                    if(activeCommand != null)
                        activeCommand = activeCommand.execute(chatId, message, connection, this);
                }


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
