package by.kosolobov.bshop.command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandDefiner {
    private static final Logger log = LogManager.getLogger(CommandDefiner.class);
    private static final String DEFAULT_ERROR_COMMAND = "MAIN_MENU";

    public static SimpleCommand define(String strCommand) {
        CommandType type;
        try {
            type = CommandType.valueOf(strCommand);
        } catch (IllegalArgumentException e) {
            log.log(Level.ERROR, "Illegal command type! Redirecting to main menu! Error message: {}", e.getMessage());
            type = CommandType.valueOf(DEFAULT_ERROR_COMMAND);
        }

        return type.getCommand();
    }
}
