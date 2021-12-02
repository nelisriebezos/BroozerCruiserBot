package com.nelisriebezos.broozercruiserbot.persistence.service;

import com.nelisriebezos.broozercruiserbot.domain.Trip;
import com.nelisriebezos.broozercruiserbot.persistence.SessionManager;
import com.nelisriebezos.broozercruiserbot.persistence.dao.RitHibernateDAO;

import java.util.List;

public class RitService {
    private final SessionManager sessionManager = new SessionManager();

    public RitService() {
    }

    public boolean save(Trip trip) {
        RitHibernateDAO ritHibernateDAO = new RitHibernateDAO(sessionManager.createSession());
        return ritHibernateDAO.save(trip);
    }

    public boolean update(Trip trip) {
        RitHibernateDAO ritHibernateDAO = new RitHibernateDAO(sessionManager.createSession());
        return ritHibernateDAO.update(trip);
    }

    public boolean delete(Trip trip) {
        RitHibernateDAO ritHibernateDAO = new RitHibernateDAO(sessionManager.createSession());
        return ritHibernateDAO.delete(trip);
    }

    public Trip findById(int id) {
        RitHibernateDAO ritHibernateDAO = new RitHibernateDAO(sessionManager.createSession());
        return ritHibernateDAO.findById(id);
    }

    public List<Trip> findAll() {
        RitHibernateDAO ritHibernateDAO = new RitHibernateDAO(sessionManager.createSession());
        return ritHibernateDAO.findAll();
    }
}
