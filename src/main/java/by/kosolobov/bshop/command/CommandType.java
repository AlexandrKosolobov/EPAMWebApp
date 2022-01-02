package by.kosolobov.bshop.command;

public enum CommandType {
//    CHECK_USER(),
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
