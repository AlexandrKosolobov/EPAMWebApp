package by.kosolobov.bshop.command.impl;

import by.kosolobov.bshop.command.SimpleCommand;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignOutCommand implements SimpleCommand {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().invalidate();
        return "index.jsp";
    }
}
