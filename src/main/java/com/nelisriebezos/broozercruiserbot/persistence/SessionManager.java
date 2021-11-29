package com.nelisriebezos.broozercruiserbot.persistence;

import com.nelisriebezos.broozercruiserbot.Safe;
import com.nelisriebezos.broozercruiserbot.Secrets;
import com.nelisriebezos.broozercruiserbot.persistence.dao.CarHibernateDAO;
import com.nelisriebezos.broozercruiserbot.persistence.dao.ChauffeurHibernateDAO;
import com.nelisriebezos.broozercruiserbot.persistence.dao.TankSessionHibernateDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionManager {

    public SessionManager() {}

    public Session createSession() throws HibernateException {

//        Configuration cfg = new Configuration();
//        cfg.configure("hibernate.cfg.xml"); //hibernate config xml file name
//        String newUserName, newPassword;//set them as per your needs
//        cfg.getProperties().setProperty("hibernate.connection.password",newPassword);
//        cfg.getProperties().setProperty("hibernate.connection.username",newUserName);
//        sessionFactory = cfg.buildSessionFactory();

//        potentieel .configre verplaatsen

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.setProperty("hibernate.connection.user", "postgres");
        configuration.setProperty("hibernate.connection.password", Safe.getDbPassword().getPassword());
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory.getCurrentSession();
    }
}
