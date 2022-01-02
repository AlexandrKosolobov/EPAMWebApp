package by.kosolobov.bshop.entity;

import java.sql.Date;
import java.sql.Time;

public class Book {
    private final int clientId;
    private final int barberId;
    private final int serviceId;
    private final Date bookDate;
    private final Time bookTime;
    private final boolean isActive;

    public Book(int clientId, int barberId, int serviceId, Date bookDate, Time bookTime, boolean isActive) {
        this.clientId = clientId;
        this.barberId = barberId;
        this.serviceId = serviceId;
        this.bookDate = bookDate;
        this.bookTime = bookTime;
        this.isActive = isActive;
    }

    public int getClientId() {
        return clientId;
    }

    public int getBarberId() {
        return barberId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public Date getBookDate() {
        return bookDate;
    }

    public Time getBookTime() {
        return bookTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public static class BookBuilder {
        private int clientId;
        private int barberId;
        private int serviceId;
        private Date bookDate;
        private Time bookTime;
        private boolean isActive;

        public void setClientId(int clientId) {
            this.clientId = clientId;
        }

        public void setBarberId(int barberId) {
            this.barberId = barberId;
        }

        public void setServiceId(int serviceId) {
            this.serviceId = serviceId;
        }

        public void setBookDate(Date bookDate) {
            this.bookDate = bookDate;
        }

        public void setBookTime(Time bookTime) {
            this.bookTime = bookTime;
        }

        public void setActive(boolean active) {
            isActive = active;
        }

        public Book build() {
            return new Book(clientId, barberId, serviceId, bookDate, bookTime, isActive);
        }
    }
}
