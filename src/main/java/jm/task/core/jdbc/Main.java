package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.*;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

        //  userService.createUsersTable();
        //   userService.createCompanyTable();
       // userService.createCarTable();
        userDaoHibernate.checkCache();
//        List<Address> address = new ArrayList<>();
//        Address address1 = new Address("Minsk");
//        address.add(address1);

//        userService.saveUser("Name1", "LastName1", (byte) 22,
//                new StudentAddress("Minsk", "Belarus", "Russia"),
//                new Company("Company1", address),
//                new Car("Car1"));
//        userService.saveUser("Name2", "LastName2", (byte) 51,
//                new StudentAddress("Minsk", "Belarus", "Russia"),
//                new Company("Company2", address),
//               new Car("Car1"));

//        userService.saveUser("Name3", "LastName3", (byte) 22, new StudentAddress("Minsk", "Belarus", "Russia"));
//        userService.saveUser("Name4", "LastName4", (byte) 60, new StudentAddress("Minsk", "Belarus", "Russia"));
//        userService.saveUser("Name5", "LastName5", (byte) 80, new StudentAddress("Minsk", "Belarus", "Russia"));

      //  userService.removeUserById(4);
      //  userService.getAllUsers();
      //  userService.cleanUsersTable();
        //  userService.dropUsersTable();

     //   userService.checkNPusOne();
    }
}
