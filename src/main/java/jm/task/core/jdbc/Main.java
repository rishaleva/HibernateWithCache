package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.StudentAddress;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

        userService.createUsersTable();
        //userDaoHibernate.checkCache();

        userService.saveUser("Name1", "LastName1", (byte) 22, new StudentAddress("Minsk", "Belarus", "Russia"));
        userService.saveUser("Name2", "LastName2", (byte) 51, new StudentAddress("Minsk", "Belarus", "Russia"));
//        userService.saveUser("Name3", "LastName3", (byte) 22, new StudentAddress("Minsk", "Belarus", "Russia"));
//        userService.saveUser("Name4", "LastName4", (byte) 60, new StudentAddress("Minsk", "Belarus", "Russia"));
//        userService.saveUser("Name5", "LastName5", (byte) 80, new StudentAddress("Minsk", "Belarus", "Russia"));

      //  userService.removeUserById(4);
      //  userService.getAllUsers();
      //  userService.cleanUsersTable();
        //  userService.dropUsersTable();


    }
}
