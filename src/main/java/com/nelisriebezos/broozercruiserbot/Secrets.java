package com.nelisriebezos.broozercruiserbot;

public class Secrets {
        private String TelegramBotToken;
        private String password;

        public String getTelegramBotToken() {
            return TelegramBotToken;
        }

        public void setTelegramBotToken(String telegramBotToken) {
            TelegramBotToken = telegramBotToken;
        }

        public String getPassword() {return password;}

        public void setPassword(String password) {this.password = password;}
}
