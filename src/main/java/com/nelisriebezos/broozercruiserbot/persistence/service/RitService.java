package com.nelisriebezos.broozercruiserbot.persistence.service;

import com.nelisriebezos.broozercruiserbot.domain.Rit;
import com.nelisriebezos.broozercruiserbot.persistence.SessionManager;
import com.nelisriebezos.broozercruiserbot.persistence.dao.RitHibernateDAO;

import java.util.List;

public class RitService {
    private final SessionManager sessionManager = new SessionManager();

    public RitService() {
    }

    public boolean save(Rit rit) {
        RitHibernateDAO ritHibernateDAO = new RitHibernateDAO(sessionManager.createSession());
        return ritHibernateDAO.save(rit);
    }

    public boolean update(Rit rit) {
        RitHibernateDAO ritHibernateDAO = new RitHibernateDAO(sessionManager.createSession());
        return ritHibernateDAO.update(rit);
    }

    public boolean delete(Rit rit) {
        RitHibernateDAO ritHibernateDAO = new RitHibernateDAO(sessionManager.createSession());
        return ritHibernateDAO.delete(rit);
    }

    public Rit findById(int id) {
        RitHibernateDAO ritHibernateDAO = new RitHibernateDAO(sessionManager.createSession());
        return ritHibernateDAO.findById(id);
    }

    public List<Rit> findAll() {
        RitHibernateDAO ritHibernateDAO = new RitHibernateDAO(sessionManager.createSession());
        return ritHibernateDAO.findAll();
    }
}
