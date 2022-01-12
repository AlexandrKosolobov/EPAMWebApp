package by.kosolobov.bshop.command.impl;

import by.kosolobov.bshop.command.SimpleCommand;
import by.kosolobov.bshop.dao.MainDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.kosolobov.bshop.sql.MySQLQueryContainer.COLUMN_SERVICE_NAME;
import static by.kosolobov.bshop.sql.MySQLQueryContainer.TABLE_SERVICE;

public class AddServiceCommand implements SimpleCommand {
    private static final Logger log = LogManager.getLogger(AddServiceCommand.class);
    private static final String SERVICE_NAME = "service_name";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        MainDao dao = MainDao.SERVICE_DAO;
        String serviceName = String.valueOf(req.getAttribute(SERVICE_NAME));

        dao.insert(TABLE_SERVICE, COLUMN_SERVICE_NAME)
                .values(serviceName)
                .execute();

        return "service_list.jsp";
    }
}
