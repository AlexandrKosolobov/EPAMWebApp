package by.kosolobov.bshop.entity;

import java.time.LocalDateTime;

public class Book {
    private final LocalDateTime dateTime;
    private final int clientUserId;
    private final int barberUserId;
    private final int serviceId;

    public Book(LocalDateTime dateTime, int clientUserId, int barberUserId, int serviceId) {
        this.dateTime = dateTime;
        this.clientUserId = clientUserId;
        this.barberUserId = barberUserId;
        this.serviceId = serviceId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public int getClientUserId() {
        return clientUserId;
    }

    public int getBarberUserId() {
        return barberUserId;
    }

    public int getServiceId() {
        return serviceId;
    }
}
