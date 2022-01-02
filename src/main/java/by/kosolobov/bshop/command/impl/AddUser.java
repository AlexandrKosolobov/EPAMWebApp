package by.kosolobov.bshop.command.impl;

import by.kosolobov.bshop.command.SimpleCommand;
import by.kosolobov.bshop.dao.MainDao;
import jakarta.servlet.http.HttpServletRequest;

public class AddUser implements SimpleCommand {
    @Override
    public String execute(HttpServletRequest req) {
        MainDao dao = MainDao.USER_DAO;
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        return null;
    }
}
