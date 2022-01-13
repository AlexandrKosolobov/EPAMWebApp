package by.kosolobov.barbershop.data.dao;

import by.kosolobov.barbershop.entity.Service;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;

import static by.kosolobov.barbershop.data.sql.SQLContainer.*;

public class UtilDao {
    private static final Logger log = LogManager.getLogger(UtilDao.class);

    public Deque<Service> getBarberServices(int barberId) {
        DaoBuilder dao = DaoBuilder.SERVICE_DAO_FULL;

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








