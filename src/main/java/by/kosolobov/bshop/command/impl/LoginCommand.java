package by.kosolobov.bshop.command.impl;

import by.kosolobov.bshop.command.SimpleCommand;
import by.kosolobov.bshop.mapper.CookieMapper;
import by.kosolobov.bshop.mapper.UserPasswordPair;
import by.kosolobov.bshop.service.UserCommandService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginCommand implements SimpleCommand {
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
        UserCommandService service = new UserCommandService();

        if (req.getCookies() != null) {
            UserPasswordPair pair = CookieMapper.mapUserCookie(req.getCookies());
            String savedPassword = service.selectUserPassword(pair.getUser());
            if (savedPassword == null) {
                return "login.jsp";
            }
            if (savedPassword.equals(pair.getPassword())) {
                req.getSession().setAttribute(USER_ID, pair.getUser().getUserId());
                req.getSession().setAttribute(USERNAME, pair.getUser().getUsername());
                req.getSession().setAttribute(ROLE, pair.getUser().getUserRole());
                req.getSession().setAttribute(FIRST_NAME, pair.getUser().getFirstName());
                req.getSession().setAttribute(SECOND_NAME, pair.getUser().getSecondName());
                req.getSession().setAttribute(SUR_NAME, pair.getUser().getSurName());
                req.getSession().setAttribute(USER_EMAIL, pair.getUser().getEmail());
                req.getSession().setAttribute(USER_PHONE, pair.getUser().getPhone());
                req.getSession().setAttribute(DESCRIPTION, pair.getUser().getDescription());

                return "person.jsp";
            }
        }

        return "login.jsp";
    }
}
