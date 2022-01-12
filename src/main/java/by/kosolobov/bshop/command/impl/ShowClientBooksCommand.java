package by.kosolobov.bshop.command.impl;

import by.kosolobov.bshop.command.SimpleCommand;
import by.kosolobov.bshop.entity.Book;
import by.kosolobov.bshop.service.BookCommandService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Deque;


public class ShowClientBooksCommand implements SimpleCommand {
    private static final String USER_ID = "user_id";
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String id = String.valueOf(req.getSession().getAttribute(USER_ID));
        Deque<Book> books = new BookCommandService().getBookByClientId(id);
        req.setAttribute("items", books);
        return "person.jsp";
    }
}
