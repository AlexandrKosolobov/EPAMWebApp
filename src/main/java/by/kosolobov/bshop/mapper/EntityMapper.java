package by.kosolobov.bshop.mapper;

import by.kosolobov.bshop.entity.Book;
import by.kosolobov.bshop.entity.Role;
import by.kosolobov.bshop.entity.Service;
import by.kosolobov.bshop.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Stack;

public class EntityMapper {
    public Stack<User> mapUser(ResultSet userSet) throws SQLException {
        Stack<User> users = new Stack<>();
        User.UserBuilder builder = new User.UserBuilder();

        while (userSet.next()) {
            builder.setUserId(userSet.getInt(1));
            builder.setUsername(userSet.getString(2));
            builder.setUserRole(Role.valueOf(userSet.getString(4)));
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

    public Stack<Service> mapService(ResultSet serviceSet) throws SQLException {
        Stack<Service> services = new Stack<>();

        while (serviceSet.next()) {
            services.push(new Service(serviceSet.getInt(1), serviceSet.getString(2)));
        }

        serviceSet.close();
        return services;
    }

    public Stack<Book> mapBook(ResultSet bookSet) throws SQLException {
        Stack<Book> books = new Stack<>();
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
