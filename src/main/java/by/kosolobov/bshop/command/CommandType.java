package by.kosolobov.bshop.command;

import by.kosolobov.bshop.command.impl.CheckUser;

public enum CommandType {
    CHECK_USER(new CheckUser())//,
//    ADD_USER(),
//    DELETE_USER,
    ;

    private SimpleCommand command;

    private CommandType(SimpleCommand command) {
        this.command = command;
    }

    public SimpleCommand getCommand() {
        return command;
    }
}
