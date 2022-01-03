package by.kosolobov.bshop.command.impl;

import by.kosolobov.bshop.command.SimpleCommand;
import by.kosolobov.bshop.dao.MainDao;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UpdateUser implements SimpleCommand {
    private static final Logger log = LogManager.getLogger(UpdateUser.class);

    @Override
    public String execute(HttpServletRequest req) {
        MainDao dao = MainDao.USER_DAO;

        return null;
    }
}
