package com.nelisriebezos.broozercruiserbot.persistence.service;

import com.nelisriebezos.broozercruiserbot.domain.Car;
import com.nelisriebezos.broozercruiserbot.persistence.SessionManager;
import com.nelisriebezos.broozercruiserbot.persistence.dao.CarHibernateDAO;

import java.util.List;

public class CarService {
    private final SessionManager sessionManager = new SessionManager();

    public CarService() {
    }

    public boolean save(Car car) {
        CarHibernateDAO carHibernateDAO = new CarHibernateDAO(sessionManager.createSession());
        return carHibernateDAO.save(car);
    }

    public boolean update(Car car) {
        CarHibernateDAO carHibernateDAO = new CarHibernateDAO(sessionManager.createSession());
        return carHibernateDAO.update(car);
    }

    public boolean delete(Car car) {
        CarHibernateDAO carHibernateDAO = new CarHibernateDAO(sessionManager.createSession());
        return carHibernateDAO.delete(car);
    }

    public Car findById(int id) {
        CarHibernateDAO carHibernateDAO = new CarHibernateDAO(sessionManager.createSession());
        return carHibernateDAO.findById(id);
    }

    public List<Car> findAll() {
        CarHibernateDAO carHibernateDAO = new CarHibernateDAO(sessionManager.createSession());
        return carHibernateDAO.findAll();
    }


}
