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

    @Override
    public String execute(HttpServletRequest req) {
        MainDao dao = MainDao.USER_DAO;
        User user = null;
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Map<String, String> columnValue = new HashMap<>();

        columnValue.put("username", username);
        columnValue.put("password", password);

        try {
            ArrayDeque<User> users = dao.select(TABLE_USER, COLUMNS_USER)
                    .where(columnValue)
                    .executeSql();

            if (users.isEmpty()) {
                return "login.jsp";
            }

            user = users.pop();
        } catch (SQLException e) {
            log.log(Level.ERROR, "Error checking user: {}", e.getMessage());
        }

        assert user != null;
        req.setAttribute("user_id", user.getUserId());
        req.setAttribute("username", user.getUsername());
        req.setAttribute("user_role", user.getUserRole());
        req.setAttribute("first_name", user.getFirstName());
        req.setAttribute("second_name", user.getSecondName());
        req.setAttribute("sur_name", user.getSurName());
        req.setAttribute("user_email", user.getEmail());
        req.setAttribute("user_phone", user.getPhone());
        req.setAttribute("user_desc", user.getDescription());

        return "person.jsp";
    }
}
