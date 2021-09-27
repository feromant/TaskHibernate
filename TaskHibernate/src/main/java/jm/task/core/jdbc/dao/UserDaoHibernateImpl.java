package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(10), " +
                    "lastname VARCHAR(10), " +
                    "age TINYINT)").executeUpdate();
            transaction.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
            Util.rollbackQuietly(transaction);
        } finally {
            Util.closeSession(session);
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
            Util.rollbackQuietly(transaction);
        } finally {
            Util.closeSession(session);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
            Util.rollbackQuietly(transaction);
        } finally {
            Util.closeSession(session);
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
            Util.rollbackQuietly(transaction);
        } finally {
            Util.closeSession(session);
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        Transaction transaction = null;
        List<User> users = new ArrayList<>();
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            users = (List<User>) session.createQuery("from User").getResultList();
            transaction.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
            Util.rollbackQuietly(transaction);
        } finally {
            Util.closeSession(session);
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = Util.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            transaction.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
            Util.rollbackQuietly(transaction);
        } finally {
            Util.closeSession(session);
        }
    }
}
