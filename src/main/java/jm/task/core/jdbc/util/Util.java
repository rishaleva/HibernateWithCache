package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class Util {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                configuration.addAnnotatedClass(User.class);
                configuration.configure("hibernate.cfg.xml");
                sessionFactory = configuration.buildSessionFactory();
                System.out.println("SessionFactory was SUCCESSFULLY built");
            } catch (Exception e) {
                System.out.println("SessionFactory was NOT built");
            }
        }
        return sessionFactory;
    }
}


