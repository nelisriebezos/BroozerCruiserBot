package com.nelisriebezos.broozercruiserbot.persistence.service;

import com.nelisriebezos.broozercruiserbot.domain.Person;
import com.nelisriebezos.broozercruiserbot.persistence.SessionManager;
import com.nelisriebezos.broozercruiserbot.persistence.dao.ChauffeurHibernateDAO;

import java.util.List;

public class ChauffeurService {
    private final SessionManager sessionManager = new SessionManager();

    public ChauffeurService() {
    }

    public boolean save(Person person) {
        ChauffeurHibernateDAO chauffeurHibernateDAO = new ChauffeurHibernateDAO(sessionManager.createSession());
        return chauffeurHibernateDAO.save(person);
    }

    public boolean update(Person person) {
        ChauffeurHibernateDAO chauffeurHibernateDAO = new ChauffeurHibernateDAO(sessionManager.createSession());
        return chauffeurHibernateDAO.update(person);
    }

    public boolean delete(Person person) {
        ChauffeurHibernateDAO chauffeurHibernateDAO = new ChauffeurHibernateDAO(sessionManager.createSession());
        return chauffeurHibernateDAO.delete(person);
    }

    public Person findById(int id) {
        ChauffeurHibernateDAO chauffeurHibernateDAO = new ChauffeurHibernateDAO(sessionManager.createSession());
        return chauffeurHibernateDAO.findById(id);
    }

    public List<Person> findAll() {
        ChauffeurHibernateDAO chauffeurHibernateDAO = new ChauffeurHibernateDAO(sessionManager.createSession());
        return chauffeurHibernateDAO.findAll();
    }
}
