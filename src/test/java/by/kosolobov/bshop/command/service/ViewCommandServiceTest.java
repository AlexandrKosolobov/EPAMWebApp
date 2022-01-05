package by.kosolobov.bshop.command.service;

import by.kosolobov.bshop.entity.Service;
import org.junit.jupiter.api.Test;

import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

class ViewCommandServiceTest {

    @Test
    void getBarberServices() {
        ViewCommandService service = new ViewCommandService();

        Deque<Service> services = service.getBarberServices(2);

        for (Service s : services) {
            System.out.println(s);
        }
    }
}