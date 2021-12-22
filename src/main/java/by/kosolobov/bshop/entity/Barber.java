package by.kosolobov.bshop.entity;

import java.util.List;

public class Barber extends User{
    private final String description;
    private final List<Service> services;

    public Barber(int userId,
                  String username,
                  String password,
                  String firstName,
                  String secondName,
                  String serName,
                  String description,
                  List<Service> services) {
        super(userId, username, password, firstName, secondName, serName);
        this.description = description;
        this.services = services;
    }

    public String getDescription() {
        return description;
    }

    public List<Service> getServices() {
        return services;
    }
}
