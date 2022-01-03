package by.kosolobov.bshop.command.impl;

import by.kosolobov.bshop.command.SimpleCommand;
import by.kosolobov.bshop.dao.MainDao;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

import static by.kosolobov.bshop.sql.MySQLQueryContainer.COLUMNS_USER_MIN;
import static by.kosolobov.bshop.sql.MySQLQueryContainer.TABLE_USER;

public class AddUser implements SimpleCommand {
    private static final Logger log = LogManager.getLogger(AddUser.class);

    @Override
    public String execute(HttpServletRequest req) {
        MainDao dao = MainDao.USER_DAO;
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String userRole = String.valueOf(req.getAttribute("user_role"));

        try {
            dao.insert(TABLE_USER, COLUMNS_USER_MIN)
                    .values(username, password, userRole)
                    .execute();
        } catch (SQLException e) {
            log.log(Level.ERROR, "Adding user failed: {}", e.getMessage());
        }

        return "person.jsp";
    }
}
