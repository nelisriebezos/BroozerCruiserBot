package com.nelisriebezos.broozercruiserbot.domain.service;

import com.nelisriebezos.broozercruiserbot.BroozerCruiserBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Connection;

public interface BotCommand {
    void reset();
    BotCommand execute(String chatId, String message, Connection connection, BroozerCruiserBot bot) throws TelegramApiException;
    boolean match(String message);


//    admin functies:
//    toevoegen van een nieuwe auto <>
//    verwijderen van een auto
//    verwijderen van een rit
//    toevoegen van een persoon
//    verwijderen van een persoon
//    verwijderen van een tanksession

//    gebruiker functies:
//    commands menu en de help functie

//    toevoegen van een nieuwe tanksessie
//    toevoegen van een rit
//    aanpassen van een rit
//    uitrekenen hoeveel iedereen moet betalen
}
