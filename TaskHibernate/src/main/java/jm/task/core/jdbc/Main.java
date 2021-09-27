package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

public class Main {

    public static void main(String[] args) {
        Util.getSessionFactory();
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Рулон", "Обоев", (byte) 32);
        userService.saveUser("Ушат", "Помоев", (byte) 20);
        userService.saveUser("Улов", "Налимов", (byte) 63);
        userService.saveUser("Букет", "Левкоев", (byte) 45);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.shutdown();
    }
}
