package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Util {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/taskjdbc";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1111";

    private static boolean initialized;

    public static void initDriver() {
        if (!initialized) {
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Can't initialize JDBC driver");
            }
            initialized = true;
        }
    }

    public static Connection getConnection() throws SQLException {
        initDriver();
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        conn.setAutoCommit(false);
        return conn;
    }

    public static void rollbackQuietly(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
                System.out.println("JDBC Transaction rolled back successfully");
            } catch (SQLException erb) {
                System.out.println("SQLException in rollback" + erb.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ignored) {
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ignored) {
            }
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ignored) {
            }
        }
    }
    /*======================Configuration for Hibernate===============================*/

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder =
                        new StandardServiceRegistryBuilder();

                Map<String, String> settings = new HashMap<>();
                settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/taskjdbc");
                settings.put("hibernate.connection.username", "root");
                settings.put("hibernate.connection.password", "1111");
                settings.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
                settings.put("hibernate.show_sql", "true");
                settings.put("hibernate.hbm2ddl.auto", "create-drop");

                registryBuilder.applySettings(settings);
                registry = registryBuilder.build();
                MetadataSources sources = new MetadataSources(registry)
                        .addAnnotatedClass(User.class);

                sessionFactory = sources.buildMetadata().buildSessionFactory();

            } catch (Exception e) {
                System.out.println("SessionFactory creation failed");
                shutdown();
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static void closeSession(Session session) {
        if (session != null) {
            try {
                session.close();
            } catch (HibernateException ignored) {
            }
        }
    }

    public static void rollbackQuietly(Transaction tx) {
        if (tx != null) {
            try {
                tx.rollback();
                System.out.println("JTA Transaction rolled back successfully");
            } catch (HibernateException he) {
                System.out.println("HibernateException in rollback" + he.getMessage());
            }
        }
    }
}
