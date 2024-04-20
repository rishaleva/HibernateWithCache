package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.*;

import java.util.Collection;
import java.util.List;

public interface UserDao {
    void createUsersTable();

    void createCompanyTable();

    void dropUsersTable();

    void saveCompany(String name, List<Address> address);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
    void checkNPusOne();

   void createCarTable();

    void saveUser(String name, String lastName, byte age , StudentAddress studentAddress, Company company, Car car);
}
