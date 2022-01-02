package by.kosolobov.bshop.command;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public interface SimpleCommand {
    String execute(HttpServletRequest req);
}
