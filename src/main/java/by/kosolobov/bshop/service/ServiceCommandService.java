package by.kosolobov.bshop.service;

import by.kosolobov.bshop.dao.MainDao;
import by.kosolobov.bshop.entity.Service;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

import static by.kosolobov.bshop.sql.MySQLQueryContainer.*;

public class ServiceCommandService {
    private static final Logger log = LogManager.getLogger(ServiceCommandService.class);
    private final MainDao dao = MainDao.SERVICE_DAO;

    public Service getServiceByName(String name) {
        return getService(COLUMN_SERVICE_NAME, name);
    }

    public Service getServiceById(String id) {
        return getService(COLUMN_SERVICE_ID, id);
    }

    private Service getService(String column, String value) {
        try {
            return (Service) dao.select(TABLE_SERVICE, COLUMNS_SERVICE)
                    .where(column, value)
                    .executeSql()
                    .pop();
        } catch (SQLException e) {
            log.log(Level.WARN, "SELECT: Service does not exist: {}", e.getMessage(), e);
        }
        return null;
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
