package by.kosolobov.barbershop.base.command.impl;

import by.kosolobov.barbershop.base.command.Command;
import by.kosolobov.barbershop.data.dao.DaoBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateUserCommand implements Command {
    private static final Logger log = LogManager.getLogger(UpdateUserCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        DaoBuilder dao = DaoBuilder.USER_DAO;

        return null;
    }
}
