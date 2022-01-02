package by.kosolobov.bshop.command;

import by.kosolobov.bshop.command.impl.AddUser;
import by.kosolobov.bshop.command.impl.CheckUser;
import by.kosolobov.bshop.command.impl.DeleteUser;

public enum CommandType {
    CHECK_USER(new CheckUser()),
    ADD_USER(new AddUser()),
    DELETE_USER(new DeleteUser()),
    ;

    private SimpleCommand command;

    private CommandType(SimpleCommand command) {
        this.command = command;
    }

    public SimpleCommand getCommand() {
        return command;
    }
}
