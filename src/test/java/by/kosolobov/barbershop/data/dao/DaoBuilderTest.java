package by.kosolobov.barbershop.data.dao;

import by.kosolobov.barbershop.entity.Service;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Deque;

import static by.kosolobov.barbershop.data.sql.SQLContainer.COLUMNS_SERVICE;
import static by.kosolobov.barbershop.data.sql.SQLContainer.TABLE_SERVICE;

class DaoBuilderTest {
    DaoBuilder dao = DaoBuilder.SERVICE_DAO;

    void select() throws SQLException {
        Deque<Service> services = dao.select(TABLE_SERVICE, COLUMNS_SERVICE).executeSql();
        for (Service service : services) {
            System.out.printf("%d : %s%n", service.getServiceId(), service.getServiceName());
        }
    }

    void insert() throws SQLException {
        dao.insert(TABLE_SERVICE,  "service_name")
                .values("test")
                .execute();
    }

    void update() throws SQLException {
        dao.update(TABLE_SERVICE)
                .set("service_name", "test")
                .where("service_name", "test")
                .execute();
    }

    void delete() throws SQLException {
        dao.delete(TABLE_SERVICE)
                .where("service_name", "test")
                .execute();
    }

    @Test
    void execute() throws SQLException {
        select();
        insert();
        select();
        update();
        select();
        delete();
        select();
    }
}