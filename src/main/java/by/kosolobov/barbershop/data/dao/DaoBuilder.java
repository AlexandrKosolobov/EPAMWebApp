package by.kosolobov.barbershop.data.dao;

import by.kosolobov.barbershop.data.ConnectionPool;
import by.kosolobov.barbershop.entity.Book;
import by.kosolobov.barbershop.entity.Service;
import by.kosolobov.barbershop.entity.User;
import by.kosolobov.barbershop.util.mapper.EntityMapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public enum DaoBuilder {
    USER_DAO {
        public List<User> executeSql() throws SQLException {
            builder.append(";");
            List<User> users = null;
            log.log(Level.INFO, "USER_DAO: Executing SQL:\n    {}", builder);

            try (Connection connection = ConnectionPool.getInstance().getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(builder.toString())) {
                 users = ENTITY_MAPPER.mapUser(resultSet);
            }

            builder.delete(0, builder.length());
            return users;
        }

    },
    SERVICE_DAO {
        public List<Service> executeSql() throws SQLException {
            builder.append(";");
            List<Service> services = null;
            log.log(Level.INFO, "SERVICE_DAO: Executing SQL:\n    {}", builder);

            try (Connection connection = ConnectionPool.getInstance().getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(builder.toString())) {
                 services = ENTITY_MAPPER.mapService(resultSet);
            }

            builder.delete(0, builder.length());
            return services;
        }

    },
    BOOK_DAO {
        public List<Book> executeSql() throws SQLException {
            builder.append(";");
            List<Book> books = null;
            log.log(Level.INFO, "BOOK_DAO: Executing SQL:\n    {}", builder);

            try (Connection connection = ConnectionPool.getInstance().getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(builder.toString())) {
                 books = ENTITY_MAPPER.mapBook(resultSet);
            }

            builder.delete(0, builder.length());
            return books;
        }

    },
    SERVICE_DAO_FULL {
        public List<Service> executeSql() throws SQLException {
            builder.append(";");
            List<Service> services = null;
            log.log(Level.INFO, "SERVICE_DAO_FULL: Executing SQL:\n    {}", builder);

            try (Connection connection = ConnectionPool.getInstance().getConnection();
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(builder.toString())) {
                 services = ENTITY_MAPPER.mapServiceFull(resultSet);
            }

            builder.delete(0, builder.length());
            return services;
        }
    };
    private static final Logger log = LogManager.getLogger(DaoBuilder.class);
    private static final EntityMapper ENTITY_MAPPER = new EntityMapper();
    final StringBuilder builder = new StringBuilder();

    public abstract <E> List<E> executeSql() throws SQLException;

    public DaoBuilder select(String table, String... columns) {
        builder.append("SELECT");

        for (String col : columns) {
            builder.append(" %s,".formatted(col));
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(" FROM %s ".formatted(table));

        return this;
    }

    public DaoBuilder insert(String table, String... columns) {
        builder.append("INSERT %s(".formatted(table));

        for (String col : columns) {
            builder.append(" %s,".formatted(col));
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(") ");

        return this;
    }

    public DaoBuilder update(String table) {
        builder.append("UPDATE %s ".formatted(table));

        return this;
    }

    public DaoBuilder delete(String table) {
        builder.append("DELETE FROM %s ".formatted(table));

        return this;
    }

    public DaoBuilder where(String column, String value) {
        builder.append("WHERE %s = '%s' ".formatted(column, value));

        return this;
    }

    public DaoBuilder andWhere(String column, String value) {
        builder.append("AND %s = '%s' ".formatted(column, value));

        return this;
    }

    public DaoBuilder values(String... values) {
        builder.append("VALUES (");

        for (String val : values) {
            builder.append(" '%s',".formatted(val));
        }
        builder.deleteCharAt(builder.length() - 1);

        builder.append(")");

        return this;
    }

    public DaoBuilder set(String column, String value) {
        builder.append("SET %s = '%s' ".formatted(column, value));

        return this;
    }

    public DaoBuilder andSet(String column, String value) {
        builder.append(", %s = %s ".formatted(column, value));

        return this;
    }

    public DaoBuilder join(String table) {
        builder.append("JOIN %s ".formatted(table));

        return this;
    }

    public DaoBuilder onStart(String column1, String column2) {
        builder.append("ON (%s = %s".formatted(column1, column2));

        return this;
    }

    public DaoBuilder andOn(String column1, String column2) {
        builder.append(", %s = %s".formatted(column1, column2));

        return this;
    }

    public DaoBuilder onEnd() {
        builder.append(") ");

        return this;
    }

    public boolean execute() {
        builder.append(";");
        log.log(Level.INFO, "Executing:\n    {}", builder);

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(builder.toString());
        } catch (SQLException e) {
            log.log(Level.ERROR, "EXECUTE ERROR: {}", e.getMessage(), e);
            return false;
        }

        builder.delete(0, builder.length());
        return true;
    }

    public List<Map<String, String>> executeRow(String... columns) {
        builder.append(";");
        Map<String, String> map = new HashMap<>();
        List<Map<String, String>> result = new ArrayList<>();
        log.log(Level.INFO, "Executing:\n    {}", builder);

        try (Connection connection = ConnectionPool.getInstance().getConnection();
             Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(builder.toString())) {

            while (resultSet.next()) {
                for (int i = 0; i < columns.length; i++) {
                    map.put(columns[i], resultSet.getString(columns[i]));
                }
                result.add(map);
            }
        } catch (SQLException e) {
            log.log(Level.ERROR, "EXECUTE ERROR: {}", e.getMessage(), e);
        } finally {
            builder.delete(0, builder.length());
        }

        return result;
    }
}
