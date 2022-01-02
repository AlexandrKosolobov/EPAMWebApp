package by.kosolobov.bshop.dao;

import by.kosolobov.bshop.entity.Service;
import by.kosolobov.bshop.entity.User;
import by.kosolobov.bshop.mapper.EntityMapper;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Stack;

import static by.kosolobov.bshop.sql.MySQLQueryContainer.COLUMNS_SERVICE;
import static by.kosolobov.bshop.sql.MySQLQueryContainer.TABLE_SERVICE;
import static org.junit.jupiter.api.Assertions.*;

class MainDaoTest {
    MainDao dao = MainDao.SERVICE_DAO;

    void select() throws SQLException {
        Stack<Service> services = dao.select(TABLE_SERVICE, COLUMNS_SERVICE).executeSql();
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
        HashMap<String, String> whereMap = new HashMap<>();
        HashMap<String, String> setMap = new HashMap<>();
        whereMap.put("service_name", "test");
        setMap.put("service_name", "test");
        dao.update(TABLE_SERVICE)
                .set(setMap)
                .where(whereMap)
                .execute();
    }

    void delete() throws SQLException {
        HashMap<String, String> whereMap = new HashMap<>();
        whereMap.put("service_name", "test");
        dao.delete(TABLE_SERVICE)
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