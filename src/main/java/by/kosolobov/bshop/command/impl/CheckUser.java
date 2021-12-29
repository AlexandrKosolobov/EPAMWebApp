package by.kosolobov.bshop.command.impl;

import by.kosolobov.bshop.command.SimpleCommand;
import by.kosolobov.bshop.dao.UserDao;
import by.kosolobov.bshop.entity.User;
import jakarta.servlet.http.HttpServletRequest;

public class CheckUser implements SimpleCommand {
    private User user;

    @Override
    public boolean execute(HttpServletRequest req) {
        UserDao ud = new UserDao();
        if (ud.checkUser(req.getParameter("username"), req.getParameter("password"))) {
            user = ud.getUserByLogIn(req.getParameter("username"), req.getParameter("password"));
            return true;
        }
        return false;
    }

    @Override
    public String getRedirectPath() {
        return "/person.jsp?username=%s&first_name=%s&second_name=%s&surname=%s&is_barber=%s&"
                .formatted(user.getUsername(), user.getFirstName(), user.getSecondName(),
                        user.getSurName(), "no");
    }
}
