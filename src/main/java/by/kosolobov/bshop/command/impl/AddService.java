package by.kosolobov.bshop.command.impl;

import by.kosolobov.bshop.command.SimpleCommand;
import by.kosolobov.bshop.dao.MainDao;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

import static by.kosolobov.bshop.sql.MySQLQueryContainer.COLUMNS_SERVICE_MIN;
import static by.kosolobov.bshop.sql.MySQLQueryContainer.TABLE_SERVICE;

public class AddService implements SimpleCommand {
    private static final Logger log = LogManager.getLogger(AddService.class);
    private static final String SERVICE_NAME = "service_name";

    @Override
    public String execute(HttpServletRequest req) {
        MainDao dao = MainDao.SERVICE_DAO;
        String serviceName = String.valueOf(req.getAttribute(SERVICE_NAME));

        try {
            dao.insert(TABLE_SERVICE, COLUMNS_SERVICE_MIN)
                    .values(serviceName)
                    .execute();
        } catch (SQLException e) {
            log.log(Level.ERROR, "Adding service failed: {}", e.getMessage());
        }

        return "service_list.jsp";
    }
}
