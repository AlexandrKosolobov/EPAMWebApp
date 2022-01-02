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
import java.util.ArrayDeque;

public enum MainDao {
    USER_DAO {

        public ArrayDeque<User> executeSql() throws SQLException {
            builder.append(";");
            Connection connection = ConnectionPool.getInstance().getConnection();
            ResultSet resultSet = null;
            ArrayDeque<User> users = null;

            try (Statement statement = connection.createStatement()) {
                resultSet = statement.executeQuery(builder.toString());
                users = ENTITY_MAPPER.mapUser(resultSet);
            } finally {
                ConnectionPool.getInstance().releaseConnection(connection);
            }

            log.log(Level.INFO, "Executing SQL:\n    {} success", builder);
            builder.delete(0, builder.length());
            return users;
        }

    },
    SERVICE_DAO {

        public ArrayDeque<Service> executeSql() throws SQLException {
            builder.append(";");
            Connection connection = ConnectionPool.getInstance().getConnection();
            ResultSet resultSet = null;
            ArrayDeque<Service> services = null;

            try (Statement statement = connection.createStatement()) {
                resultSet = statement.executeQuery(builder.toString());
                services = ENTITY_MAPPER.mapService(resultSet);
            } finally {
                ConnectionPool.getInstance().releaseConnection(connection);
            }

            log.log(Level.INFO, "Executing SQL:\n    {} success", builder);
            builder.delete(0, builder.length());
            return services;
        }

    },
    BOOK_DAO {

        public ArrayDeque<Book> executeSql() throws SQLException {
            builder.append(";");
            Connection connection = ConnectionPool.getInstance().getConnection();
            ResultSet resultSet = null;
            ArrayDeque<Book> books = null;

            try (Statement statement = connection.createStatement()) {
                resultSet = statement.executeQuery(builder.toString());
                books = ENTITY_MAPPER.mapBook(resultSet);
            } finally {
                ConnectionPool.getInstance().releaseConnection(connection);
            }

            log.log(Level.INFO, "Executing SQL:\n    {} success", builder);
            builder.delete(0, builder.length());
            return books;
        }

    };
    private static final Logger log = LogManager.getLogger(MainDao.class);
    private static final EntityMapper ENTITY_MAPPER = new EntityMapper();
    final StringBuilder builder = new StringBuilder();

    public abstract <E> ArrayDeque<E> executeSql() throws SQLException;

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

        try (Statement statement = connection.createStatement()) {
            statement.execute(builder.toString());
        } catch (SQLException e) {
            log.log(Level.ERROR, "Executing:\n    {} error: {}", builder, e.getMessage());
            return false;
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        log.log(Level.INFO, "Executing:\n    {} success", builder);
        builder.delete(0, builder.length());
        return true;
    }
}
