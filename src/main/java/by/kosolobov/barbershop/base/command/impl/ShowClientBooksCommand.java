package by.kosolobov.barbershop.base.command.impl;

import by.kosolobov.barbershop.base.command.Command;
import by.kosolobov.barbershop.data.dao.BookDao;
import by.kosolobov.barbershop.entity.Book;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Deque;


public class ShowClientBooksCommand implements Command {
    private static final String USER_ID = "user_id";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = String.valueOf(req.getSession().getAttribute(USER_ID));
        Deque<Book> books = new BookDao().selectBookByClientId(id);
        req.setAttribute("items", books);
        return "person.jsp";
    }
}
