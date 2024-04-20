package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
//import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.*;

import java.util.Collection;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao hyber = new UserDaoHibernateImpl();

    public void createUsersTable() {
        hyber.createUsersTable();
    }


    public void dropUsersTable() {
        hyber.dropUsersTable();
    }

    @Override
    public void createCompanyTable() {
        hyber.createCompanyTable();
    }

    @Override
    public void saveCompany(String name, List<Address> address) {
        hyber.saveCompany(name, address);
    }

    public void saveUser(String name, String lastName, byte age, StudentAddress studentAddress, Company company, Car car) {
        hyber.saveUser(name,lastName,age, studentAddress, company, car);
    }

    public void removeUserById(long id) {
        hyber.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return hyber.getAllUsers();
    }

    public void cleanUsersTable() {
        hyber.cleanUsersTable();
    }

    @Override
    public void checkNPusOne() {
        hyber.checkNPusOne();
    }

    @Override
    public void createCarTable() {
        hyber.createCarTable();
    }

}
