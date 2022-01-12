package by.kosolobov.bshop.command;

import by.kosolobov.bshop.command.impl.*;

public enum CommandType {
    CHECK_USER(new CheckUserCommand()),
    ADD_USER(new AddUserCommand()),
    DELETE_USER(new DeleteUserCommand()),
    SHOW_CLIENT_BOOK(new ShowClientBooksCommand()),
    ILLEGAL_NAME(new IllegalNameCommand()),
    LOGIN(new LoginCommand())
    ;

    private final SimpleCommand command;

    CommandType(SimpleCommand command) {
        this.command = command;
    }

    public SimpleCommand getCommand() {
        return command;
    }
}
