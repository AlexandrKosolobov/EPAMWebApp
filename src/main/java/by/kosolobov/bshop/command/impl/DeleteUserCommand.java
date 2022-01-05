package by.kosolobov.bshop.command.impl;

import by.kosolobov.bshop.command.SimpleCommand;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteUserCommand implements SimpleCommand {
    private static final Logger log = LogManager.getLogger(DeleteUserCommand.class);


    @Override
    public String execute(HttpServletRequest req) {
        return null;
    }
}
