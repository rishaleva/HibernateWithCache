package jm.task.core.jdbc.util;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.Address;
import jm.task.core.jdbc.model.Car;
import jm.task.core.jdbc.model.Company;
import jm.task.core.jdbc.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {

    private static SessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger(UserDaoHibernateImpl.class);

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Company.class);
                configuration.addAnnotatedClass(Car.class);
                configuration.addAnnotatedClass(Address.class);
                configuration.configure("hibernate.cfg.xml");
                sessionFactory = configuration.buildSessionFactory();
                logger.warn("SessionFactory was built");
            } catch (Exception e) {
                logger.warn("SessionFactory was NOT built", e);
            }
        }
        return sessionFactory;
    }
}


