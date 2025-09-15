package commands;

import java.util.HashMap;
import java.util.Map;

public enum CommandEnum {
    LIST("list", new HandlerList()),
    MARK("mark", new HandlerMark()),
    UNMARK("unmark", new HandlerUnmark()),
    DELETE("delete", new HandlerDelete()),
    TODO("todo", new HandlerTodo()),
    EVENT("event", new CreateTaskEvent()),
    DEADLINE("deadline", new CreateTaskDeadline()),
    CHECK_OCCUR("check-occur", new HandlerCheckOccur()),
    FIND("find", new HandlerFind()),
    BYE("bye");

    public final String label;
    public final CommandHandler handler;

    private CommandEnum(String label, CommandHandler handler) {
        this.label = label;
        this.handler = handler;
    }

    private CommandEnum(String label) {
        this.label = label;
        this.handler = new HandlerList(); // Just assign a random handler
    }

    private static final Map<String, CommandEnum> LOOKUP = new HashMap<>();

    static {
        for (CommandEnum c : values()) {
            LOOKUP.put(c.label, c);
        }
    }

    public static CommandEnum fromString(String label) {
        return LOOKUP.get(label);
    }
}
