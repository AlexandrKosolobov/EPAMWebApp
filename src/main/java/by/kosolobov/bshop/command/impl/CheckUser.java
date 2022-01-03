package by.kosolobov.bshop.command.impl;

import by.kosolobov.bshop.command.SimpleCommand;
import by.kosolobov.bshop.dao.MainDao;
import by.kosolobov.bshop.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayDeque;

import static by.kosolobov.bshop.sql.MySQLQueryContainer.COLUMNS_USER;
import static by.kosolobov.bshop.sql.MySQLQueryContainer.TABLE_USER;

public class CheckUser implements SimpleCommand {
    private static final Logger log = LogManager.getLogger(CheckUser.class);
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
    public String execute(HttpServletRequest req) {
        MainDao dao = MainDao.USER_DAO;
        User user = null;
        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);
        Map<String, String> columnValue = new HashMap<>();

        columnValue.put(USERNAME, username);
        columnValue.put(PASSWORD, password);

        try {
            ArrayDeque<User> users = dao.select(TABLE_USER, COLUMNS_USER)
                    .where(columnValue)
                    .executeSql();

            if (users.isEmpty()) {
                return "login.jsp";
            }

            user = users.pop();
        } catch (SQLException e) {
            log.log(Level.ERROR, "Error checking user: {}", e.getMessage(), e);
        }

        assert user != null;
        req.setAttribute(USER_ID, user.getUserId());
        req.setAttribute(USERNAME, user.getUsername());
        req.setAttribute(ROLE, user.getUserRole());
        req.setAttribute(FIRST_NAME, user.getFirstName());
        req.setAttribute(SECOND_NAME, user.getSecondName());
        req.setAttribute(SUR_NAME, user.getSurName());
        req.setAttribute(USER_EMAIL, user.getEmail());
        req.setAttribute(USER_PHONE, user.getPhone());
        req.setAttribute(DESCRIPTION, user.getDescription());

        return "person.jsp";
    }
}
