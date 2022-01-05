package by.kosolobov.bshop.dao;

import by.kosolobov.bshop.entity.Service;
import by.kosolobov.bshop.entity.User;
import by.kosolobov.bshop.mapper.EntityMapper;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Deque;
import java.util.HashMap;
import java.util.ArrayDeque;

import static by.kosolobov.bshop.sql.MySQLQueryContainer.COLUMNS_SERVICE;
import static by.kosolobov.bshop.sql.MySQLQueryContainer.TABLE_SERVICE;
import static org.junit.jupiter.api.Assertions.*;

class MainDaoTest {
    MainDao dao = MainDao.SERVICE_DAO;

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