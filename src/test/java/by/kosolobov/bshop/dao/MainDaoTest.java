package by.kosolobov.bshop.dao;

import by.kosolobov.bshop.entity.Service;
import by.kosolobov.bshop.entity.User;
import by.kosolobov.bshop.mapper.EntityMapper;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class MainDaoTest {
    MainDao dao = MainDao.SERVICE_DAO;

    void select() throws SQLException {
        Stack<Service> services = dao.select("services", "service_id", "service_name").executeSql();
        for (Service service : services) {
            System.out.println("%d : %s".formatted(service.getServiceId(), service.getServiceName()));
        }
    }

    void insert() throws SQLException {
        boolean success = dao.insert("services",  "service_name")
                .values("test")
                .execute();
        System.out.println(success);
    }

    void update() throws SQLException {
        HashMap<String, String> whereMap = new HashMap<>();
        HashMap<String, String> setMap = new HashMap<>();
        whereMap.put("service_name", "test");
        setMap.put("service_name", "test");
        boolean success = dao.update("services")
                .set(setMap)
                .where(whereMap)
                .execute();
        System.out.println(success);
    }

    void delete() throws SQLException {
        HashMap<String, String> whereMap = new HashMap<>();
        whereMap.put("service_name", "test");
        boolean success = dao.delete("services")
                .where(whereMap)
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