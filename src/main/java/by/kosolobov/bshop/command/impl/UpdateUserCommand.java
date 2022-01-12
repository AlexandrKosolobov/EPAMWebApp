package by.kosolobov.bshop.command.impl;

import by.kosolobov.bshop.command.SimpleCommand;
import by.kosolobov.bshop.dao.MainDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateUserCommand implements SimpleCommand {
    private static final Logger log = LogManager.getLogger(UpdateUserCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        MainDao dao = MainDao.USER_DAO;

        return null;
    }
}
