package commands;

import java.util.HashMap;
import java.util.Map;

public enum CommandType {
    LIST("list", new HandlerList()),
    MARK("mark", new HandlerMark()),
    UNMARK("unmark", new HandlerUnmark()),
    DELETE("delete", new HandlerDelete()),
    TODO("todo", new CreateTaskTodo()),
    EVENT("event", new CreateTaskEvent()),
    DEADLINE("deadline", new CreateTaskDeadline()),
    CHECK_OCCUR("check-occur", new HandlerCheckOccur()),
    FIND("find", new HandlerFind()),
    BYE("bye");

    private final String LABEL;
    private final CommandHandler HANDLER;

    private CommandType(String label, CommandHandler handler) {
        LABEL = label;
        HANDLER = handler;
    }

    private CommandType(String label) {
        LABEL = label;
        HANDLER = null;
    }

    public CommandHandler getHandler() {
        return HANDLER;
    }

    private static final Map<String, CommandType> LOOKUP = new HashMap<>();

    static {
        for (CommandType c : values()) {
            LOOKUP.put(c.LABEL, c);
        }
    }

    public static CommandType fromString(String label) {
        return LOOKUP.get(label);
    }
}
