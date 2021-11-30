package com.nelisriebezos.broozercruiserbot.persistence.dao;

import com.nelisriebezos.broozercruiserbot.BroozerCruiserBot;
import com.nelisriebezos.broozercruiserbot.Exceptions.NoChauffeurException;
import com.nelisriebezos.broozercruiserbot.domain.Chauffeur;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ChauffeurHibernateDAO implements ChauffeurDAO{
    private final Session session;
    private Transaction transaction = null;
    private static final Logger LOG = LoggerFactory.getLogger(ChauffeurHibernateDAO.class);


    public ChauffeurHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Chauffeur chauffeur) {
        try {
            transaction = session.beginTransaction();
            session.save(chauffeur);
            session.getTransaction().commit();
            LOG.info(chauffeur + " is saved");
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Chauffeur chauffeur) {
        try {
            transaction = session.beginTransaction();
            session.update(chauffeur);
            session.getTransaction().commit();
            LOG.info(chauffeur + " is updated");
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Chauffeur chauffeur) {
        try {
            transaction = session.beginTransaction();
            session.delete(chauffeur);
            session.getTransaction().commit();
            LOG.info(chauffeur + " is deleted");
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOG.error(e.getMessage());
            return false;
        }
    }

    @Override
    public Chauffeur findById(int id) {
        session.beginTransaction();
        Chauffeur chauffeur = session.load(Chauffeur.class, id);
        session.getTransaction().commit();
        return chauffeur;
    }

    @Override
    public List<Chauffeur> findAll() {
        session.beginTransaction();
        List chauffeurs = this.session.createQuery(
          "select c from Chauffeur c").getResultList();
        session.getTransaction().commit();
//        if (chauffeurs.isEmpty()) throw new NoChauffeurException(chauffeurs + " is empty");
        return chauffeurs;
    }
}
