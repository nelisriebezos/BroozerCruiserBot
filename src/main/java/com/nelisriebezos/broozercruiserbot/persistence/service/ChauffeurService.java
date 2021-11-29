package com.nelisriebezos.broozercruiserbot.persistence.service;

import com.nelisriebezos.broozercruiserbot.domain.Chauffeur;
import com.nelisriebezos.broozercruiserbot.persistence.SessionManager;
import com.nelisriebezos.broozercruiserbot.persistence.dao.ChauffeurHibernateDAO;

import java.util.List;

public class ChauffeurService {
    private final SessionManager sessionManager = new SessionManager();

    public ChauffeurService() {
    }

    public boolean save(Chauffeur chauffeur) {
        ChauffeurHibernateDAO chauffeurHibernateDAO = new ChauffeurHibernateDAO(sessionManager.createSession());
        return chauffeurHibernateDAO.save(chauffeur);
    }

    public boolean update(Chauffeur chauffeur) {
        ChauffeurHibernateDAO chauffeurHibernateDAO = new ChauffeurHibernateDAO(sessionManager.createSession());
        return chauffeurHibernateDAO.update(chauffeur);
    }

    public boolean delete(Chauffeur chauffeur) {
        ChauffeurHibernateDAO chauffeurHibernateDAO = new ChauffeurHibernateDAO(sessionManager.createSession());
        return chauffeurHibernateDAO.delete(chauffeur);
    }

    public Chauffeur findById(int id) {
        ChauffeurHibernateDAO chauffeurHibernateDAO = new ChauffeurHibernateDAO(sessionManager.createSession());
        return chauffeurHibernateDAO.findById(id);
    }

    public List<Chauffeur> findAll() {
        ChauffeurHibernateDAO chauffeurHibernateDAO = new ChauffeurHibernateDAO(sessionManager.createSession());
        return chauffeurHibernateDAO.findAll();
    }
}
