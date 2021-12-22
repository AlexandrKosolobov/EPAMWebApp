package by.kosolobov.bshop.entity;

public class User {
    private int userId;
    private final String username;
    private final String password; //todo: remove password definition from class User and add encryption of password while adding to database
    private final String firstName;
    private final String secondName;
    private final String serName;

    public User(String username, String password, String firstName, String secondName, String serName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.serName = serName;
    }

    public User(int userId, String username, String password, String firstName, String secondName, String serName) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.serName = serName;
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

    public String getSerName() {
        return serName;
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
        return serName != null ? serName.equals(user.serName) : user.serName == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (serName != null ? serName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{username:%s, password:%s, firstName:%s, secondName:%s, serName:%s}".formatted(username, password, firstName, secondName, serName);
    }

    public static class UserBuilder {
        private int userId = 0;
        private String username;
        private String password;
        private String firstName;
        private String secondName;
        private String serName;

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

        public UserBuilder serName(String value) {
            serName = value;
            return this;
        }

        public User build() {
            if (userId == 0) {
                return new User(username, password, firstName, secondName, serName);
            } else {
                return new User(userId, username, password, firstName, secondName, serName);
            }
        }
    }
}
