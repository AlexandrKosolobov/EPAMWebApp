package by.kosolobov.barbershop.util.mapper;

import by.kosolobov.barbershop.entity.Book;
import by.kosolobov.barbershop.entity.Service;
import by.kosolobov.barbershop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ArrayDeque;

import static by.kosolobov.barbershop.data.sql.SQLContainer.*;

public class EntityMapper {
    public List<User> mapUser(ResultSet userSet) throws SQLException {
        List<User> users = new ArrayList<>();
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

            users.add(builder.build());
        }

        userSet.close();
        return users;
    }

    public List<Service> mapService(ResultSet serviceSet) throws SQLException {
        List<Service> services = new ArrayList<>();

        while (serviceSet.next()) {
            services.add(new Service(serviceSet.getInt(1), serviceSet.getString(2)));
        }

        serviceSet.close();
        return services;
    }

    public List<Service> mapServiceFull(ResultSet serviceSet) throws SQLException {
        List<Service> services = new ArrayList<>();

        while (serviceSet.next()) {
            services.add(new Service(serviceSet.getInt(COLUMN_SERVICE_ID), serviceSet.getString(COLUMN_SERVICE_NAME),
                    serviceSet.getInt(COLUMN_BARBER_ID), serviceSet.getInt(COLUMN_PRICE)));
        }

        serviceSet.close();
        return services;
    }

    public List<Book> mapBook(ResultSet bookSet) throws SQLException {
        List<Book> books = new ArrayList<>();
        Book.BookBuilder builder = new Book.BookBuilder();

        while (bookSet.next()) {
            builder.setClientId(bookSet.getInt(1));
            builder.setBarberId(bookSet.getInt(2));
            builder.setServiceId(bookSet.getInt(3));
            builder.setBookDate(bookSet.getDate(4));
            builder.setBookTime(bookSet.getTime(5));
            builder.setActive(bookSet.getBoolean(6));

            books.add(builder.build());
        }

        bookSet.close();
        return books;
    }
}
