package by.kosolobov.barbershop.base.command;

import by.kosolobov.barbershop.base.command.impl.*;

public enum CommandType {
    CHECK_USER(new LoginCommand()),
    ADD_USER(new AddUserCommand()),
    DELETE_USER(new DeleteUserCommand()),
    SHOW_CLIENT_BOOK(new ShowClientBooksCommand()),
    ILLEGAL_NAME(new IllegalNameCommand()),
    LOGIN(new LoginCommand())
    ;

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
