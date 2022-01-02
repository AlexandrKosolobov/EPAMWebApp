package by.kosolobov.bshop.dao;

import by.kosolobov.bshop.connector.ConnectionPool;
import by.kosolobov.bshop.entity.Book;
import by.kosolobov.bshop.entity.Service;
import by.kosolobov.bshop.entity.User;
import by.kosolobov.bshop.mapper.EntityMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Stack;

public enum MainDao {
    USER_DAO {

        public Stack<User> executeSql() throws SQLException {
            builder.append(";");
            Connection connection = ConnectionPool.getInstance().getConnection();
            ResultSet resultSet = null;
            Stack<User> users;

            try (Statement statement = connection.createStatement()) {
                resultSet = statement.executeQuery(builder.toString());
                users = ENTITY_MAPPER.mapUser(resultSet);
            } finally {
                ConnectionPool.getInstance().releaseConnection(connection);
            }

            log.log(Level.INFO, "Executing SQL:\n {} success", builder);
            builder.delete(0, builder.length());
            return users;
        }

    },
    SERVICE_DAO {

        public Stack<Service> executeSql() throws SQLException {
            builder.append(";");
            Connection connection = ConnectionPool.getInstance().getConnection();
            ResultSet resultSet = null;
            Stack<Service> services;

            try (Statement statement = connection.createStatement()) {
                resultSet = statement.executeQuery(builder.toString());
                services = ENTITY_MAPPER.mapService(resultSet);
            } finally {
                ConnectionPool.getInstance().releaseConnection(connection);
            }

            log.log(Level.INFO, "Executing SQL:\n {} success", builder);
            builder.delete(0, builder.length());
            return services;
        }

    },
    BOOK_DAO {

        public Stack<Book> executeSql() throws SQLException {
            builder.append(";");
            Connection connection = ConnectionPool.getInstance().getConnection();
            ResultSet resultSet = null;
            Stack<Book> books;

            try (Statement statement = connection.createStatement()) {
                resultSet = statement.executeQuery(builder.toString());
                books = ENTITY_MAPPER.mapBook(resultSet);
            } finally {
                ConnectionPool.getInstance().releaseConnection(connection);
            }

            log.log(Level.INFO, "Executing SQL:\n {} success", builder);
            builder.delete(0, builder.length());
            return books;
        }

    };
    private static final Logger log = LogManager.getLogger(MainDao.class);
    private static final EntityMapper ENTITY_MAPPER = new EntityMapper();
    final StringBuilder builder = new StringBuilder();

    public abstract <E> Stack<E> executeSql() throws SQLException;

    public MainDao select(String table, String... columns) {
        builder.append("SELECT");

        for (String col : columns) {
            builder.append(" %s,".formatted(col));
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(" FROM %s ".formatted(table));

        return this;
    }

    public MainDao insert(String table, String... columns) {
        builder.append("INSERT %s(".formatted(table));

        for (String col : columns) {
            builder.append(" %s,".formatted(col));
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(") ");

        return this;
    }

    public MainDao update(String table) {
        builder.append("UPDATE %s ".formatted(table));

        return this;
    }

    public MainDao delete(String table) {
        builder.append("DELETE FROM %s ".formatted(table));

        return this;
    }

    public MainDao where(Map<String, String> columnValue) {
        builder.append("WHERE");

        for (Map.Entry<String, String> entry : columnValue.entrySet()) {
            builder.append(" %s = '%s' AND".formatted(entry.getKey(), entry.getValue()));
        }
        int last = builder.length();
        builder.delete(last - 4, last);
        builder.append(" ");

        return this;
    }

    public MainDao values(String... values) {
        builder.append("VALUES (");

        for (String val : values) {
            builder.append(" '%s',".formatted(val));
        }
        builder.deleteCharAt(builder.length() - 1);

        builder.append(")");

        return this;
    }

    public MainDao set(Map<String, String> columnValue) {
        builder.append("SET");

        for (Map.Entry<String, String> entry : columnValue.entrySet()) {
            builder.append(" %s = '%s'".formatted(entry.getKey(), entry.getValue()));
        }
        builder.append(" ");

        return this;
    }

    public boolean execute() throws SQLException {
        builder.append(";");
        Connection connection = ConnectionPool.getInstance().getConnection();
        boolean execute = false;

        try (Statement statement = connection.createStatement()) {
            execute = statement.execute(builder.toString());
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        log.log(Level.INFO, "Executing:\n {} success", builder);
        builder.delete(0, builder.length());
        return execute;
    }
}
