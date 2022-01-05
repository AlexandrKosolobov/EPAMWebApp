package by.kosolobov.bshop.command.impl;

import by.kosolobov.bshop.command.SimpleCommand;
import by.kosolobov.bshop.command.service.UserCommandService;
import by.kosolobov.bshop.dao.MainDao;
import by.kosolobov.bshop.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

import static by.kosolobov.bshop.sql.MySQLQueryContainer.COLUMNS_USER_MIN;
import static by.kosolobov.bshop.sql.MySQLQueryContainer.TABLE_USER;

public class AddUserCommand implements SimpleCommand {
    private static final Logger log = LogManager.getLogger(AddUserCommand.class);
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ROLE = "user_role";

    @Override
    public String execute(HttpServletRequest req) {
        MainDao dao = MainDao.USER_DAO;
        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);
        String userRole = String.valueOf(req.getAttribute(ROLE));

        UserCommandService service = new UserCommandService();

        User user = service.getUserByUsername(username);
        if (user == null) {
            return "signup.jsp";
        }
        service.insertUser(username, password, userRole);

        return "person.jsp";
    }
}
