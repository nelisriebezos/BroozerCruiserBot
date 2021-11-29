package com.nelisriebezos.broozercruiserbot.persistence.service;

import com.nelisriebezos.broozercruiserbot.domain.TankSession;
import com.nelisriebezos.broozercruiserbot.persistence.SessionManager;
import com.nelisriebezos.broozercruiserbot.persistence.dao.TankSessionHibernateDAO;

import java.util.List;

public class TankSessionService {
    private final SessionManager sessionManager = new SessionManager();

    public TankSessionService() {
    }

    public boolean save(TankSession tankSession) {
        TankSessionHibernateDAO tankSessionHibernateDAO = new TankSessionHibernateDAO(sessionManager.createSession());
        return tankSessionHibernateDAO.save(tankSession);
    }

    public boolean update(TankSession tankSession) {
        TankSessionHibernateDAO tankSessionHibernateDAO = new TankSessionHibernateDAO(sessionManager.createSession());
        return tankSessionHibernateDAO.update(tankSession);
    }

    public boolean delete(TankSession tankSession) {
        TankSessionHibernateDAO tankSessionHibernateDAO = new TankSessionHibernateDAO(sessionManager.createSession());
        return tankSessionHibernateDAO.delete(tankSession);
    }

    public TankSession findById(int id) {
        TankSessionHibernateDAO tankSessionHibernateDAO = new TankSessionHibernateDAO(sessionManager.createSession());
        return tankSessionHibernateDAO.findById(id);
    }

    public List<TankSession> findAll() {
        TankSessionHibernateDAO tankSessionHibernateDAO = new TankSessionHibernateDAO(sessionManager.createSession());
        return tankSessionHibernateDAO.findAll();
    }
}
