package by.kosolobov.bshop.mapper;

import by.kosolobov.bshop.entity.User;

public class UserPasswordPair {
    private User user;
    private String password;

    public UserPasswordPair(User user, String password) {
        this.user = user;
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
