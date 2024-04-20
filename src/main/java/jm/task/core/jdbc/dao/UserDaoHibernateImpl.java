package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.*;
import jm.task.core.jdbc.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import javax.persistence.EntityGraph;

import java.util.*;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();
    private Transaction transaction = null;
    private final String sqlCreateTable = "CREATE TABLE IF NOT EXISTS \"user\"" +
            "(id BIGSERIAL PRIMARY KEY NOT NULL, name VARCHAR(45), " +
            "lastName VARCHAR(45), age SMALLINT, " +
            "company_id BIGINT, " +
            "FOREIGN KEY (company_id) REFERENCES \"company\" (id),"+
            "FOREIGN KEY (car_id) REFERENCES \"car\" (id))";

    private final String sqlCompanyCreateTable = "CREATE TABLE IF NOT EXISTS \"company\"" +
            "(id BIGSERIAL PRIMARY KEY NOT NULL, name VARCHAR(45), " +
            "FOREIGN KEY (address_id) REFERENCES \"address\" (id))";


    private final String sqlCarCreateTable = "CREATE TABLE IF NOT EXISTS \"car\"" +
            "(id BIGSERIAL PRIMARY KEY NOT NULL, name VARCHAR(45))";
    private final String sqlDropTable = "DROP TABLE IF EXISTS \"user\"";
    private final String sqlCleanTable = "TRUNCATE TABLE \"user\"";

    private static final Logger logger = LogManager.getLogger(UserDaoHibernateImpl.class);

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.createSQLQuery(sqlCreateTable).addEntity(User.class).executeUpdate();
            transaction.commit();
            logger.info("Table was created");
        } catch (Exception e) {
            logger.warn("Table was NOT created", e.getCause());
        }
    }

    public void createCarTable() {
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            session.createSQLQuery(sqlCarCreateTable).addEntity(Car.class).executeUpdate();
            transaction.commit();
            logger.info("Table was created");
        } catch (Exception e) {
            logger.warn("Table was NOT created", e.getCause());
        }
    }


    @Override
    public void createCompanyTable() {
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            session.createSQLQuery(sqlCompanyCreateTable).addEntity(Company.class).executeUpdate();
            session.createSQLQuery(sqlCreateTable).addEntity(User.class).executeUpdate();
            transaction.commit();

            logger.info("Table was created");
        } catch (Exception e) {
            logger.warn("Table was NOT created", e.getCause());
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            session.createSQLQuery(sqlDropTable).addEntity(User.class).executeUpdate();
            transaction.commit();

            logger.info("Table was dropped");
        } catch (Exception e) {
            logger.warn("Table was NOT dropped", e.getCause());
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age, StudentAddress studentAddress, Company company, Car car) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age, studentAddress, company, car);
            user.setStudentAddress(studentAddress);
            session.save(user);
            session.save(company);
            session.save(car);
            session.save(company.getAddress().get(0));
            transaction.commit();

            logger.info("User: {} was saved", user);
        } catch (Exception e) {
            logger.warn("User was NOT saved", e.getCause());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveCompany(String name, List <Address> address) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Company company1 = new Company(name, address);
            session.save(company1);
            transaction.commit();

            logger.info("User: {} was saved", company1);
        } catch (Exception e) {
            logger.warn("User was NOT saved", e.getCause());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();

            logger.info("User with ID {} was deleted", id);
        } catch (Exception e) {
            logger.warn("User with ID was NOT deleted", e.getCause());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            userList = session.createQuery("from User", User.class).getResultList();
            transaction.commit();

            logger.info("Table was printed");
        } catch (Exception e) {
            logger.warn("Table was NOT printed", e.getCause());
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            session.createSQLQuery(sqlCleanTable).addEntity(User.class).executeUpdate();
            transaction.commit();

            logger.info("All users was deleted from database");
        } catch (Exception e) {
            logger.warn("users was NOT deleted from db", e.getCause());
        }
    }

    public void checkCache() {
        try (Session session = sessionFactory.openSession()) {
            List<Address> address = new ArrayList<>();
            Address address1 = new Address("Minsk");
            address.add(address1);
            transaction = session.beginTransaction();

            Company company = new Company("test", address);
            Car car = new Car("test");
            User user = new User("test", "test", (byte) 10,new StudentAddress("test", "test", "test"),
                    company,car);

            session.save(user);
            session.save(company);
            session.save(car);
            session.save(company.getAddress().get(0));
            logger.info("--------------------SAVED");
            session.flush();
            User user2 = session.get(User.class, 1L);
            transaction.commit();
        } catch (Exception e) {
            logger.warn("checkedCache", e.getCause());
        }
        logger.info("-------------------SECOND------------------");
        try (Session session1 = sessionFactory.openSession()) {

            transaction = session1.beginTransaction();
           User user = session1.get(User.class, 1L);
            logger.info("CHECKED");
            transaction.commit();
        } catch (Exception e) {
            logger.warn("checkedCache", e.getCause());
        }
    }

    public void checkNPusOne() {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            EntityGraph entityGraph = session.getEntityGraph("WithCompanyAndCar");
            Map<String, Object> properties = new HashMap<>();
            properties.put("jakarta.persistence.loadhgraph", entityGraph);
            User user = session.find(User.class, 1L, properties);

//            Query<User> userQuery = session.createQuery("from User", User.class);
//            userQuery.list();

            transaction.commit();
        } catch (Exception e) {
            logger.warn("N+1", e);
        }
    }
}
