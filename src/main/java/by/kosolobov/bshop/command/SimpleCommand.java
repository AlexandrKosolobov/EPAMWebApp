package by.kosolobov.bshop.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface SimpleCommand {
    String execute(HttpServletRequest req, HttpServletResponse resp);
}
