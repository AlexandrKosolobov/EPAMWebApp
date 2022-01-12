package by.kosolobov.bshop.service;

import by.kosolobov.bshop.dao.MainDao;
import by.kosolobov.bshop.entity.Service;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;

import static by.kosolobov.bshop.sql.MySQLQueryContainer.*;

public class ViewCommandService {
    private static final Logger log = LogManager.getLogger(ViewCommandService.class);

    public Deque<Service> getBarberServices(int barberId) {
        MainDao dao = MainDao.SERVICE_DAO_FULL;

        try {
            return dao.select(TABLE_BARBER_HAS_SERVICE, "*")
                    .join(TABLE_SERVICE)
                    .onStart(TABLE_BARBER_HAS_SERVICE + "." + COLUMN_SERVICE_ID, TABLE_SERVICE + "." + COLUMN_SERVICE_ID)
                    .onEnd()
                    .where(COLUMN_BARBER_ID, String.valueOf(barberId))
                    .executeSql();
        } catch (SQLException e) {
            log.log(Level.WARN, "SELECT: Barber does not exist: {}", e.getMessage(), e);
        }

        return new ArrayDeque<>();
    }
}








