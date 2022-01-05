package by.kosolobov.bshop.mapper;

import by.kosolobov.bshop.entity.Book;
import by.kosolobov.bshop.entity.Service;
import by.kosolobov.bshop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ArrayDeque;

import static by.kosolobov.bshop.sql.MySQLQueryContainer.*;

public class EntityMapper {
    public ArrayDeque<User> mapUser(ResultSet userSet) throws SQLException {
        ArrayDeque<User> users = new ArrayDeque<>();
        User.UserBuilder builder = new User.UserBuilder();

        while (userSet.next()) {
            builder.setUserId(userSet.getInt(1));
            builder.setUsername(userSet.getString(2));
            builder.setUserRole(User.Role.valueOf(userSet.getString(4).toUpperCase(Locale.ROOT)));
            builder.setFirstName(userSet.getString(5));
            builder.setSecondName(userSet.getString(6));
            builder.setSurName(userSet.getString(7));
            builder.setEmail(userSet.getString(8));
            builder.setPhone(userSet.getString(9));
            builder.setDescription(userSet.getString(10));

            users.push(builder.build());
        }

        userSet.close();
        return users;
    }

    public ArrayDeque<Service> mapService(ResultSet serviceSet) throws SQLException {
        ArrayDeque<Service> services = new ArrayDeque<>();

        while (serviceSet.next()) {
            services.push(new Service(serviceSet.getInt(1), serviceSet.getString(2)));
        }

        serviceSet.close();
        return services;
    }

    public ArrayDeque<Service> mapServiceFull(ResultSet serviceSet) throws SQLException {
        ArrayDeque<Service> services = new ArrayDeque<>();

        while (serviceSet.next()) {
            services.push(new Service(serviceSet.getInt(COLUMN_SERVICE_ID), serviceSet.getString(COLUMN_SERVICE_NAME),
                    serviceSet.getInt(COLUMN_BARBER_ID), serviceSet.getInt(COLUMN_PRICE)));
        }

        serviceSet.close();
        return services;
    }

    public ArrayDeque<Book> mapBook(ResultSet bookSet) throws SQLException {
        ArrayDeque<Book> books = new ArrayDeque<>();
        Book.BookBuilder builder = new Book.BookBuilder();

        while (bookSet.next()) {
            builder.setClientId(bookSet.getInt(1));
            builder.setBarberId(bookSet.getInt(2));
            builder.setServiceId(bookSet.getInt(3));
            builder.setBookDate(bookSet.getDate(4));
            builder.setBookTime(bookSet.getTime(5));
            builder.setActive(bookSet.getBoolean(6));

            books.push(builder.build());
        }

        bookSet.close();
        return books;
    }
}
