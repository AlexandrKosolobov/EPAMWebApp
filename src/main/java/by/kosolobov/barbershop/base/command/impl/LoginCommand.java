package by.kosolobov.barbershop.base.command.impl;

import by.kosolobov.barbershop.base.command.Command;
import by.kosolobov.barbershop.data.dao.UserDao;
import by.kosolobov.barbershop.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class LoginCommand implements Command {
    private static final Logger log = LogManager.getLogger(LoginCommand.class);
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
    private static final String ERROR = "error";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        UserDao service = new UserDao();
        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);//todo:encrypt

        Optional<String> savedPassword = service.selectPassword(username);

        if (savedPassword.isPresent()) {
            if (!savedPassword.get().equals(password)) {
                req.setAttribute(ERROR, PASSWORD);
                return "login.jsp";
            }
        } else {
            req.setAttribute(ERROR, USERNAME);
            return "login.jsp";
        }

        Optional<User> userOptional = service.selectUserByUsername(username);

        userOptional.ifPresent(user -> {
            req.getSession().setAttribute(USER_ID, user.getUserId());
            req.getSession().setAttribute(USERNAME, user.getUsername());
            req.getSession().setAttribute(ROLE, user.getUserRole());
            req.getSession().setAttribute(FIRST_NAME, user.getFirstName());
            req.getSession().setAttribute(SECOND_NAME, user.getSecondName());
            req.getSession().setAttribute(SUR_NAME, user.getSurName());
            req.getSession().setAttribute(USER_EMAIL, user.getEmail());
            req.getSession().setAttribute(USER_PHONE, user.getPhone());
            req.getSession().setAttribute(DESCRIPTION, user.getDescription());
        });

        return "person.jsp";
    }
}
