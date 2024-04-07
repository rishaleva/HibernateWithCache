package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();
    private Transaction transaction = null;
    private final String sqlCreateTable = "CREATE TABLE IF NOT EXISTS \"user\"" +
            "(id BIGSERIAL PRIMARY KEY NOT NULL, name VARCHAR(45), " +
            "lastName VARCHAR(45), age SMALLINT) ";
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
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
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
}
