package by.kosolobov.bshop.entity;

public class User {
    private int userId;
    private final String username;
    private final String password; //todo: remove password definition from class User and add encryption of password while adding to database
    private final String firstName;
    private final String secondName;
    private final String surName;

    public User(String username, String password, String firstName, String secondName, String surName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.surName = surName;
    }

    public User(int userId, String username, String password, String firstName, String secondName, String surName) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.surName = surName;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getSurName() {
        return surName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (secondName != null ? !secondName.equals(user.secondName) : user.secondName != null) return false;
        return surName != null ? surName.equals(user.surName) : user.surName == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (surName != null ? surName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{username:%s, password:%s, firstName:%s, secondName:%s, serName:%s}".formatted(username, password, firstName, secondName, surName);
    }

    public static class UserBuilder {
        private int userId = 0;
        private String username;
        private String password;
        private String firstName;
        private String secondName;
        private String surName;

        public UserBuilder userId(int value) {
            userId = value;
            return this;
        }

        public UserBuilder username(String value) {
            username = value;
            return this;
        }

        public UserBuilder password(String value) {
            password = value;
            return this;
        }

        public UserBuilder firstName(String value) {
            firstName = value;
            return this;
        }

        public UserBuilder secondName(String value) {
            secondName = value;
            return this;
        }

        public UserBuilder surName(String value) {
            surName = value;
            return this;
        }

        public User build() {
            if (userId == 0) {
                return new User(username, password, firstName, secondName, surName);
            } else {
                return new User(userId, username, password, firstName, secondName, surName);
            }
        }
    }
}
