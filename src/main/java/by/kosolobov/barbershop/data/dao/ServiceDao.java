package by.kosolobov.barbershop.data.dao;

import by.kosolobov.barbershop.entity.Service;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;

import static by.kosolobov.barbershop.data.sql.SQLContainer.*;

public class ServiceDao {
    private static final Logger log = LogManager.getLogger(ServiceDao.class);
    private final DaoBuilder dao = DaoBuilder.SERVICE_DAO;

    public Deque<Service> selectServiceByName(String name) {
        return selectService(COLUMN_SERVICE_NAME, name);
    }

    public Deque<Service> selectServiceById(String id) {
        return selectService(COLUMN_SERVICE_ID, id);
    }

    private Deque<Service> selectService(String column, String value) {
        try {
            return dao.select(TABLE_SERVICE, COLUMNS_SERVICE)
                    .where(column, value)
                    .executeSql();
        } catch (SQLException e) {
            log.log(Level.WARN, "SELECT: Service does not exist: {}", e.getMessage(), e);
        }
        return new ArrayDeque<>();
    }

    public boolean updateService(String oldName, String newName) {
        return dao.update(TABLE_SERVICE)
                .set(COLUMN_SERVICE_NAME, newName)
                .where(COLUMN_SERVICE_NAME, oldName)
                .execute();
    }

    public boolean insertService(String name) {
        return dao.insert(TABLE_SERVICE, COLUMN_SERVICE_NAME)
                .values(name)
                .execute();
    }

    public boolean deleteService(String name) {
        return dao.delete(TABLE_SERVICE)
                .where(COLUMN_SERVICE_NAME, name)
                .execute();
    }
}
