package by.kosolobov.barbershop.base.command.impl;

import by.kosolobov.barbershop.base.command.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteUserCommand implements Command {
    private static final Logger log = LogManager.getLogger(DeleteUserCommand.class);


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }
}
