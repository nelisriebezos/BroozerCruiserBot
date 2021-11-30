package com.nelisriebezos.broozercruiserbot;

import com.nelisriebezos.broozercruiserbot.persistence.dao.CarHibernateDAO;
import com.nelisriebezos.broozercruiserbot.persistence.dao.ChauffeurHibernateDAO;
import com.nelisriebezos.broozercruiserbot.persistence.dao.TankSessionHibernateDAO;
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
    private static CarHibernateDAO carHibernateDAO;
    private static ChauffeurHibernateDAO chauffeurHibernateDAO;
    private static TankSessionHibernateDAO tankSessionHibernateDAO;

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


    public static void main(String[] args) throws Exception {
        BroozerCruiserBot bot = new BroozerCruiserBot();
        bot.startBot();
    }
}
