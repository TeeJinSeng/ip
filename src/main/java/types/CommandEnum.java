package types;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import utils.CommandHandler;

public enum CommandEnum {
    LIST("list", CommandHandler::handleList),
    MARK("mark", CommandHandler::handleMark),
    UNMARK("unmark", CommandHandler::handleUnmark),
    TODO("todo", CommandHandler::handleTodo),
    EVENT("event", CommandHandler::handleEvent),
    DEADLINE("deadline", CommandHandler::handleDeadline),
    DELETE("delete", CommandHandler::handleDelete);

    public final String label;
    public final Consumer<FormattedInput> method;

    private CommandEnum(String label, Consumer<FormattedInput> method) {
        this.label = label;
        this.method = method;
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
