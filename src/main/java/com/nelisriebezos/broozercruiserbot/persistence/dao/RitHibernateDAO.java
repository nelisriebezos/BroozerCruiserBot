package com.nelisriebezos.broozercruiserbot.persistence.dao;

import com.nelisriebezos.broozercruiserbot.domain.Trip;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RitHibernateDAO implements RitDAO{
    private final Session session;
    private Transaction transaction = null;
    private static final Logger LOG = LoggerFactory.getLogger(RitHibernateDAO.class);

    public RitHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Trip trip) {
        try {
            transaction = session.beginTransaction();
            session.save(trip);
            session.getTransaction().commit();
            LOG.info(trip + " is saved");
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
    public boolean update(Trip trip) {
        try {
            transaction = session.beginTransaction();
            session.update(trip);
            session.getTransaction().commit();
            LOG.info(trip + " is updated");
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
    public boolean delete(Trip trip) {
        try {
            transaction = session.beginTransaction();
            session.delete(trip);
            session.getTransaction().commit();
            LOG.info(trip + " is deleted");
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
    public Trip findById(int id) {
        session.beginTransaction();
        Trip trip = session.load(Trip.class, id);
        session.getTransaction().commit();
        return trip;
    }

    @Override
    public List<Trip> findAll() {
        session.beginTransaction();
        List rit = this.session.createQuery(
                "select r from Rit r").getResultList();
        session.getTransaction().commit();
//        if (rit.isEmpty()) throw new NoRitException(rit + " is empty");
        return rit;
    }
}
