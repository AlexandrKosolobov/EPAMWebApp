package by.kosolobov.barbershop.base.command.impl;

import by.kosolobov.barbershop.base.command.Command;
import by.kosolobov.barbershop.data.dao.UserDao;
import by.kosolobov.barbershop.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class AddUserCommand implements Command {
    private static final Logger log = LogManager.getLogger(AddUserCommand.class);
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ROLE = "user_role";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);
        String userRole = String.valueOf(req.getAttribute(ROLE));

        UserDao service = new UserDao();
        Optional<User> user = service.selectUserByUsername(username);

        if (user.isPresent()) {
            return "signup.jsp";
        }

        service.insertUser(username, password, userRole);
        return "person.jsp";
    }
}
