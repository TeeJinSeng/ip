package commands;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores all the available commands of the Chatbot and its handlers
 */
public enum CommandEnum {
    LIST("list", new HandlerList()),
    MARK("mark", new HandlerMark()),
    UNMARK("unmark", new HandlerUnmark()),
    DELETE("delete", new HandlerDelete()),
    TODO("todo", new HandlerTodo()),
    EVENT("event", new CreateTaskEvent()),
    DEADLINE("deadline", new CreateTaskDeadline()),
    CHECK_OCCUR("check-occur", new HandlerCheckOccur()),
    BYE("bye");

    public final String label;
    public final CommandHandler handler;

    private CommandEnum(String label, CommandHandler handler) {
        this.label = label;
        this.handler = handler;
    }

    /**
     * Handles the case specifically for {@code bye} command.
     * @param label
     */
    private CommandEnum(String label) {
        this.label = label;
        this.handler = new HandlerList();
    }

    /**
     * A lookup table that will be used by {@code fromString} method. 
     */
    private static final Map<String, CommandEnum> LOOKUP = new HashMap<>();

    static {
        for (CommandEnum c : values()) {
            LOOKUP.put(c.label, c);
        }
    }

    /**
     * Finds the field that match the String {@code label}.
     * @param label String to find
     * @return a CommandEnum field which label match the input string {@code label}
     */
    public static CommandEnum fromString(String label) {
        return LOOKUP.get(label);
    }
}
