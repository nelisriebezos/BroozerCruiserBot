package com.nelisriebezos.broozercruiserbot;

import org.checkerframework.checker.units.qual.C;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class BroozerCruiserBot extends TelegramLongPollingBot {
    private static final Logger LOG = LoggerFactory.getLogger(BroozerCruiserBot.class);
//    private static final SessionFactory factory;

    @Override
    public String getBotUsername() {
        return this.getClass().getName();
    }

    @Override
    public String getBotToken() {
        return Safe.getApiKeys().getTelegramBotToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            String message = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();

            message = message.toLowerCase();

            sendTextMessage(chatId, message);

        } catch (TelegramApiException e) {
            LOG.error(e.getMessage(), e);
        }
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

    public static Session createSession() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory.getCurrentSession();
    }

    public static void main(String[] args) throws Exception {
        BroozerCruiserBot bot = new BroozerCruiserBot();
        bot.startBot();
    }
}
