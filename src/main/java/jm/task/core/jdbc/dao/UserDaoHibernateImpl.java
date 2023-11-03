package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
   private final SessionFactory sessionFactory = Util.getSessionFactory();
   private Transaction transaction = null;
    private final String sqlCreateTable = "CREATE TABLE IF NOT EXISTS user" +
            "(id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL, name VARCHAR(45), " +
            "lastName VARCHAR(45), age TINYINT) ";
    private final String sqlDropTable = "DROP TABLE IF EXISTS user";
    private final String sqlCleanTable = "TRUNCATE TABLE user";
    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
        transaction = session.beginTransaction();
        session.createSQLQuery(sqlCreateTable).addEntity(User.class).executeUpdate();
        transaction.commit();
            System.out.println("Table was created");
    } catch (Exception e) {
            System.out.println("Table was NOT created");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(sqlDropTable).addEntity(User.class).executeUpdate();
            transaction.commit();
            System.out.println("Table was dropped");
        } catch (Exception e) {
            System.out.println("Table was NOT dropped");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()) {
           transaction= session.beginTransaction();
           User user = new User(name,lastName,age);
            session.save(user);
            transaction.commit();
            System.out.println("User : " + name + " " + lastName + " " + age + " was saved");
        } catch (Exception e) {
            System.out.println("User was NOT saved");
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {

        try (Session session = sessionFactory.openSession()) {
          transaction= session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
            System.out.println("User with ID " + id + " was deleted ");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("User with ID was NOT deleted");
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List <User> userList = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            userList = session.createQuery("from User", User.class).getResultList();
            transaction.commit();
            System.out.println("Table was printed");
        } catch (Exception e) {
            System.out.println("Table was NOT printed");
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(sqlCleanTable).addEntity(User.class).executeUpdate();
           transaction.commit();
            System.out.println("All users was deleted from database");
        } catch (Exception e) {
            System.out.println("users was NOT deleted from db");
        }
    }
}
