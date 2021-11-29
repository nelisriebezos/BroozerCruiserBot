package com.nelisriebezos.broozercruiserbot.persistence.dao;

import com.nelisriebezos.broozercruiserbot.BroozerCruiserBot;
import com.nelisriebezos.broozercruiserbot.Exceptions.NoChauffeurException;
import com.nelisriebezos.broozercruiserbot.Exceptions.NoRitException;
import com.nelisriebezos.broozercruiserbot.domain.Chauffeur;
import com.nelisriebezos.broozercruiserbot.domain.Rit;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RitHibernateDAO implements RitDAO{
    private final Session session;
    private Transaction transaction = null;
    private static final Logger LOG = LoggerFactory.getLogger(BroozerCruiserBot.class);

    public RitHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Rit rit) {
        try {
            transaction = session.beginTransaction();
            session.save(rit);
            session.getTransaction().commit();
            LOG.info(rit + " is saved");
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
    public boolean update(Rit rit) {
        try {
            transaction = session.beginTransaction();
            session.update(rit);
            session.getTransaction().commit();
            LOG.info(rit + " is updated");
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
    public boolean delete(Rit rit) {
        try {
            transaction = session.beginTransaction();
            session.delete(rit);
            session.getTransaction().commit();
            LOG.info(rit + " is deleted");
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
    public Rit findById(int id) {
        session.beginTransaction();
        Rit rit = session.load(Rit.class, id);
        session.getTransaction().commit();
        return rit;
    }

    @Override
    public List<Rit> findAll() {
        session.beginTransaction();
        List rit = this.session.createQuery(
                "select r from Rit r").getResultList();
        session.getTransaction().commit();
        if (rit.size() == 0) throw new NoRitException(rit + " is empty");
        return rit;
    }
}
