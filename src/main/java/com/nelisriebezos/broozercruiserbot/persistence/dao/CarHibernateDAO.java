package com.nelisriebezos.broozercruiserbot.persistence.dao;

import com.nelisriebezos.broozercruiserbot.BroozerCruiserBot;
import com.nelisriebezos.broozercruiserbot.Exceptions.NoCarException;
import com.nelisriebezos.broozercruiserbot.domain.Car;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CarHibernateDAO implements CarDAO{
    private final Session session;
    private Transaction transaction = null;
    private static final Logger LOG = LoggerFactory.getLogger(CarHibernateDAO.class);


    public CarHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Car car) {
        try {
            transaction = session.beginTransaction();
            session.save(car);
            session.getTransaction().commit();
            LOG.info(car + " is saved");
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
    public boolean update(Car car) {
        try {
            transaction = session.beginTransaction();
            session.update(car);
            session.getTransaction().commit();
            LOG.info(car + " is updated");
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
    public boolean delete(Car car) {
        try {
            transaction = session.beginTransaction();
            session.delete(car);
            session.getTransaction().commit();
            LOG.info(car + " is deleted");
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
    public Car findById(int id) {
        session.beginTransaction();
        Car car = session.load(Car.class, id);
        session.getTransaction().commit();
        return car;
    }

    @Override
    public List<Car> findAll() {
        session.beginTransaction();
        List cars = this.session.createQuery(
                "select c from Car c").getResultList();
        session.getTransaction().commit();
//        if (cars.isEmpty()) throw new NoCarException(cars + " is empty");
        return cars;
    }
}
