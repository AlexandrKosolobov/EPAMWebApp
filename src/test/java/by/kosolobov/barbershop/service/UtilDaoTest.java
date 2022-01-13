package by.kosolobov.barbershop.service;

import by.kosolobov.barbershop.entity.Service;
import by.kosolobov.barbershop.data.dao.UtilDao;
import org.junit.jupiter.api.Test;

import java.util.Deque;

class UtilDaoTest {

    @Test
    void getBarberServices() {
        UtilDao service = new UtilDao();

        Deque<Service> services = service.getBarberServices(2);

        for (Service s : services) {
            System.out.println(s);
        }
    }
}