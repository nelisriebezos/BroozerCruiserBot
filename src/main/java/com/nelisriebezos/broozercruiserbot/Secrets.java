package com.nelisriebezos.broozercruiserbot;

public class Secrets {
    private String TelegramBotToken;
    private String Password;

    public String getTelegramBotToken() {
        return TelegramBotToken;
    }

    public void setTelegramBotToken(String telegramBotToken) {
        TelegramBotToken = telegramBotToken;
    }

    public String getDatabasePassword() {
        return Password;
    }

    public void setDatabasePassword(String password) {
        Password = password;
    }


}
