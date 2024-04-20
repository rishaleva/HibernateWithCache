package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.*;

import java.util.Collection;
import java.util.List;

public interface UserService {
    void createUsersTable();

    void dropUsersTable();

    void createCompanyTable();
    void saveCompany(String name, List<Address> address);

    void saveUser(String name, String lastName, byte age , StudentAddress studentAddress, Company company, Car car);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
    void checkNPusOne();

    void createCarTable();
}
