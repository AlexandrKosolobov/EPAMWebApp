package by.kosolobov.bshop.mapper;

import by.kosolobov.bshop.entity.User;
import jakarta.servlet.http.Cookie;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CookieMapper {
    private static final Logger log = LogManager.getLogger(CookieMapper.class);

    public static UserPasswordPair mapUserCookie(Cookie[] cookies) {
        User.UserBuilder builder = new User.UserBuilder();
        String password = null;
        for (Cookie cookie : cookies) {
            switch (cookie.getName()) {
                case "user_id" -> builder.setUserId(Integer.parseInt(cookie.getValue()));
                case "username" -> builder.setUsername(cookie.getValue());
                case "password" -> password = cookie.getValue();
                case "user_role" -> builder.setUserRole(User.Role.valueOf(cookie.getValue()));
                case "first_name" -> builder.setFirstName(cookie.getValue());
                case "second_name" -> builder.setSecondName(cookie.getValue());
                case "surname" -> builder.setSurName(cookie.getValue());
                case "user_email" -> builder.setEmail(cookie.getValue());
                case "user_phone" -> builder.setPhone(cookie.getValue());
                case "user_desc" -> builder.setDescription(cookie.getValue());
                default -> log.log(Level.INFO, "Unknown cookie for user");
            }
        }

        UserPasswordPair pair = new UserPasswordPair(builder.build(), password);
        return pair;
    }
}
