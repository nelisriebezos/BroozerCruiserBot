package com.nelisriebezos.broozercruiserbot.persistence.dao;

import com.nelisriebezos.broozercruiserbot.BroozerCruiserBot;
import com.nelisriebezos.broozercruiserbot.Exceptions.NoTankSessionException;
import com.nelisriebezos.broozercruiserbot.domain.TankSession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TankSessionHibernateDAO implements TankSessionDAO{
    private final Session session;
    private Transaction transaction = null;
    private static final Logger LOG = LoggerFactory.getLogger(TankSessionHibernateDAO.class);


    public TankSessionHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(TankSession tankSession) {
        try {
            transaction = session.beginTransaction();
            session.save(tankSession);
            session.getTransaction().commit();
            LOG.info(tankSession + " is saved");
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
    public boolean update(TankSession tankSession) {
        try {
            transaction = session.beginTransaction();
            session.update(tankSession);
            session.getTransaction().commit();
            LOG.info(tankSession + " is updated");
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
    public boolean delete(TankSession tankSession) {
        try {
            transaction = session.beginTransaction();
            session.delete(tankSession);
            session.getTransaction().commit();
            LOG.info(tankSession + " is deleted");
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
    public TankSession findById(int id) {
        session.beginTransaction();
        TankSession tankSession = session.load(TankSession.class, id);
        session.getTransaction().commit();
        return tankSession;
    }

    @Override
    public List<TankSession> findAll() {
        session.beginTransaction();
        List tanksessions = this.session.createQuery(
                "select t from TankSession t").getResultList();
        session.getTransaction().commit();
//        if (tanksessions.isEmpty()) throw new NoTankSessionException(tanksessions + " is empty");
        return tanksessions;
    }
}
