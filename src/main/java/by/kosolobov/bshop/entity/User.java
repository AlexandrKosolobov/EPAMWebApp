package by.kosolobov.bshop.entity;

public class User {
    private final int userId;
    private final String username;
    private final Role userRole;
    private final String firstName;
    private final String secondName;
    private final String surName;
    private final String email;
    private final String phone;
    private final String description;

    public User(int userId,
                String username,
                Role userRole,
                String firstName,
                String secondName,
                String surName,
                String email,
                String phone,
                String description) {
        this.userId = userId;
        this.username = username;
        this.userRole = userRole;
        this.firstName = firstName;
        this.secondName = secondName;
        this.surName = surName;
        this.email = email;
        this.phone = phone;
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Role getUserRole() {
        return userRole;
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

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }

    public static class UserBuilder {
        private int userId;
        private String username;
        private Role userRole;
        private String firstName;
        private String secondName;
        private String surName;
        private String email;
        private String phone;
        private String description;

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setUserRole(Role userRole) {
            this.userRole = userRole;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setSecondName(String secondName) {
            this.secondName = secondName;
        }

        public void setSurName(String surName) {
            this.surName = surName;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public User build() {
            return new User(userId,
                            username,
                            userRole,
                            firstName,
                            secondName,
                            surName,
                            email,
                            phone,
                            description);
        }
    }
}
