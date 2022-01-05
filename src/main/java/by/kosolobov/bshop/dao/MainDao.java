package by.kosolobov.bshop.dao;

import by.kosolobov.bshop.connector.ConnectionPool;
import by.kosolobov.bshop.connector.ProxyConnection;
import by.kosolobov.bshop.entity.Book;
import by.kosolobov.bshop.entity.Service;
import by.kosolobov.bshop.entity.User;
import by.kosolobov.bshop.mapper.EntityMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayDeque;
import java.util.Deque;

public enum MainDao {
    USER_DAO {

        public Deque<User> executeSql() throws SQLException {
            builder.append(";");
            ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            ResultSet resultSet = null;
            ArrayDeque<User> users = null;

            try (Statement statement = connection.createStatement()) {
                resultSet = statement.executeQuery(builder.toString());
                users = ENTITY_MAPPER.mapUser(resultSet);
            }

            log.log(Level.INFO, "Executing SQL:\n    {} success", builder);
            builder.delete(0, builder.length());
            return users;
        }

    },
    SERVICE_DAO {

        public Deque<Service> executeSql() throws SQLException {
            builder.append(";");
            ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            ResultSet resultSet = null;
            ArrayDeque<Service> services = null;

            try (Statement statement = connection.createStatement()) {
                resultSet = statement.executeQuery(builder.toString());
                services = ENTITY_MAPPER.mapService(resultSet);
            }

            log.log(Level.INFO, "Executing SQL:\n    {} success", builder);
            builder.delete(0, builder.length());
            return services;
        }

    },
    BOOK_DAO {

        public Deque<Book> executeSql() throws SQLException {
            builder.append(";");
            ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            ResultSet resultSet = null;
            ArrayDeque<Book> books = null;

            try (Statement statement = connection.createStatement()) {
                resultSet = statement.executeQuery(builder.toString());
                books = ENTITY_MAPPER.mapBook(resultSet);
            }

            log.log(Level.INFO, "Executing SQL:\n    {} success", builder);
            builder.delete(0, builder.length());
            return books;
        }

    },
    SERVICE_DAO_FULL {

        public Deque<Service> executeSql() throws SQLException {
            builder.append(";");
            ProxyConnection connection = ConnectionPool.getInstance().getConnection();
            ResultSet resultSet = null;
            ArrayDeque<Service> services = null;

            try (Statement statement = connection.createStatement()) {
                resultSet = statement.executeQuery(builder.toString());
                services = ENTITY_MAPPER.mapServiceFull(resultSet);
            }

            log.log(Level.INFO, "Executing SQL:\n    {} success", builder);
            builder.delete(0, builder.length());
            return services;
        }
    };
    private static final Logger log = LogManager.getLogger(MainDao.class);
    private static final EntityMapper ENTITY_MAPPER = new EntityMapper();
    final StringBuilder builder = new StringBuilder();

    public abstract <E> Deque<E> executeSql() throws SQLException;

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

    public MainDao where(String column, String value) {
        builder.append("WHERE %s = '%s' ".formatted(column, value));

        return this;
    }

    public MainDao andWhere(String column, String value) {
        builder.append("AND %s = '%s' ".formatted(column, value));

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

    public MainDao set(String column, String value) {
        builder.append("SET %s = '%s' ".formatted(column, value));

        return this;
    }

    public MainDao andSet(String column, String value) {
        builder.append(", %s = %s ".formatted(column, value));

        return this;
    }

    public MainDao join(String table) {
        builder.append("JOIN %s ".formatted(table));

        return this;
    }

    public MainDao onStart(String column1, String column2) {
        builder.append("ON (%s = %s".formatted(column1, column2));

        return this;
    }

    public MainDao andOn(String column1, String column2) {
        builder.append(", %s = %s".formatted(column1, column2));

        return this;
    }

    public MainDao onEnd() {
        builder.append(") ");

        return this;
    }

    public boolean execute() throws SQLException {
        builder.append(";");
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute(builder.toString());
        } catch (SQLException e) {
            log.log(Level.ERROR, "Executing:\n    {} error: {}", builder, e.getMessage(), e);
            return false;
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        log.log(Level.INFO, "Executing:\n    {} success", builder);
        builder.delete(0, builder.length());
        return true;
    }

    public ResultSet rowExecuteSql() throws SQLException {
        builder.append(";");
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        ResultSet resultSet = null;
        ArrayDeque<Book> books = null;

        try (Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(builder.toString());
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        log.log(Level.INFO, "Executing SQL:\n    {} success", builder);
        builder.delete(0, builder.length());
        return resultSet;
    }
}
