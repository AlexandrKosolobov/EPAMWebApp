package by.kosolobov.bshop.entity;

public class Client extends User {
    private final String description;

    public Client(int userId,
                  String username,
                  String password,
                  String firstName,
                  String secondName,
                  String serName,
                  String description) {
        super(userId, username, password, firstName, secondName, serName);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
