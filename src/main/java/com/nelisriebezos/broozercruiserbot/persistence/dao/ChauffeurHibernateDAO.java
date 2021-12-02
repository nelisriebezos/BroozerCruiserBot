package com.nelisriebezos.broozercruiserbot.persistence.dao;

import com.nelisriebezos.broozercruiserbot.domain.Person;
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
    public boolean save(Person person) {
        try {
            transaction = session.beginTransaction();
            session.save(person);
            session.getTransaction().commit();
            LOG.info(person + " is saved");
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
    public boolean update(Person person) {
        try {
            transaction = session.beginTransaction();
            session.update(person);
            session.getTransaction().commit();
            LOG.info(person + " is updated");
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
    public boolean delete(Person person) {
        try {
            transaction = session.beginTransaction();
            session.delete(person);
            session.getTransaction().commit();
            LOG.info(person + " is deleted");
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
    public Person findById(int id) {
        session.beginTransaction();
        Person person = session.load(Person.class, id);
        session.getTransaction().commit();
        return person;
    }

    @Override
    public List<Person> findAll() {
        session.beginTransaction();
        List chauffeurs = this.session.createQuery(
          "select c from Chauffeur c").getResultList();
        session.getTransaction().commit();
//        if (chauffeurs.isEmpty()) throw new NoChauffeurException(chauffeurs + " is empty");
        return chauffeurs;
    }
}
