package by.kosolobov.bshop.command;

import by.kosolobov.bshop.command.impl.AddUserCommand;
import by.kosolobov.bshop.command.impl.CheckUserCommand;
import by.kosolobov.bshop.command.impl.DeleteUserCommand;

public enum CommandType {
    CHECK_USER(new CheckUserCommand()),
    ADD_USER(new AddUserCommand()),
    DELETE_USER(new DeleteUserCommand()),
    ;

    private SimpleCommand command;

    private CommandType(SimpleCommand command) {
        this.command = command;
    }

    public SimpleCommand getCommand() {
        return command;
    }
}
