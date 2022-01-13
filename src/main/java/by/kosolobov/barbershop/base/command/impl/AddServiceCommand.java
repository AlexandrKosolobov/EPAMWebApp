package by.kosolobov.barbershop.base.command.impl;

import by.kosolobov.barbershop.base.command.Command;
import by.kosolobov.barbershop.data.dao.DaoBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.kosolobov.barbershop.data.sql.SQLContainer.COLUMN_SERVICE_NAME;
import static by.kosolobov.barbershop.data.sql.SQLContainer.TABLE_SERVICE;

public class AddServiceCommand implements Command {
    private static final Logger log = LogManager.getLogger(AddServiceCommand.class);
    private static final String SERVICE_NAME = "service_name";

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoBuilder dao = DaoBuilder.SERVICE_DAO;
        String serviceName = String.valueOf(req.getAttribute(SERVICE_NAME));

        dao.insert(TABLE_SERVICE, COLUMN_SERVICE_NAME)
                .values(serviceName)
                .execute();

        return "service_list.jsp";
    }
}
