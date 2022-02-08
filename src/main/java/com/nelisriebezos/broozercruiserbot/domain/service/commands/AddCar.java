package com.nelisriebezos.broozercruiserbot.domain.service.commands;

import com.nelisriebezos.broozercruiserbot.domain.service.BotCommand;
import com.nelisriebezos.broozercruiserbot.domain.service.dao.CarDAO;

public class AddCar implements BotCommand {
    private CarDAO carDAO;

    public AddCar(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @Override
    public String execute() {
        return null;
    }

    @Override
    public Boolean match() {
        return null;
    }
}
