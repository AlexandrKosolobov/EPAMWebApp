package by.kosolobov.bshop.command.impl;

import by.kosolobov.bshop.command.SimpleCommand;
import by.kosolobov.bshop.dao.MainDao;
import by.kosolobov.bshop.entity.User;
import by.kosolobov.bshop.mapper.CookieMapper;
import by.kosolobov.bshop.mapper.UserPasswordPair;
import by.kosolobov.bshop.service.UserCommandService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Deque;

import static by.kosolobov.bshop.sql.MySQLQueryContainer.COLUMNS_USER;
import static by.kosolobov.bshop.sql.MySQLQueryContainer.TABLE_USER;

public class CheckUserCommand implements SimpleCommand {
    private static final Logger log = LogManager.getLogger(CheckUserCommand.class);
    private static final String USER_ID = "user_id";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ROLE = "user_role";
    private static final String FIRST_NAME = "first_name";
    private static final String SECOND_NAME = "second_name";
    private static final String SUR_NAME = "sur_name";
    private static final String USER_EMAIL = "user_email";
    private static final String USER_PHONE = "user_phone";
    private static final String DESCRIPTION = "user_desc";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        MainDao dao = MainDao.USER_DAO;
        User user = null;
        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);

        try {
            Deque<User> users = dao.select(TABLE_USER, COLUMNS_USER)
                    .where(USERNAME, username)
                    .andWhere(PASSWORD, password)
                    .executeSql();

            if (users.isEmpty()) {
                return "login.jsp";
            }

            user = users.pop();
        } catch (SQLException e) {
            log.log(Level.ERROR, "Error checking user: {}", e.getMessage(), e);
        }

        assert user != null;
        req.getSession().setAttribute(USER_ID, user.getUserId());
        req.getSession().setAttribute(USERNAME, user.getUsername());
        req.getSession().setAttribute(ROLE, user.getUserRole());
        req.getSession().setAttribute(FIRST_NAME, user.getFirstName());
        req.getSession().setAttribute(SECOND_NAME, user.getSecondName());
        req.getSession().setAttribute(SUR_NAME, user.getSurName());
        req.getSession().setAttribute(USER_EMAIL, user.getEmail());
        req.getSession().setAttribute(USER_PHONE, user.getPhone());
        req.getSession().setAttribute(DESCRIPTION, user.getDescription());

        if (req.getParameter("remember") != null) {
            resp.addCookie(new Cookie(USER_ID, String.valueOf(user.getUserId())));
            resp.addCookie(new Cookie(USERNAME, user.getUsername()));
            resp.addCookie(new Cookie(PASSWORD, password));
            resp.addCookie(new Cookie(ROLE, String.valueOf(user.getUserRole())));
            resp.addCookie(new Cookie(FIRST_NAME, user.getFirstName()));
            resp.addCookie(new Cookie(SECOND_NAME, user.getSecondName()));
            resp.addCookie(new Cookie(SUR_NAME, user.getSurName()));
            resp.addCookie(new Cookie(USER_EMAIL, user.getEmail()));
            resp.addCookie(new Cookie(USER_PHONE, user.getPhone()));
            resp.addCookie(new Cookie(DESCRIPTION, user.getDescription()));
        }

        return "person.jsp";
    }
}
