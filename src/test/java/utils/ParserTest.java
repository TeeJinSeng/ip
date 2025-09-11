package utils;

// import utils.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;

import commands.CommandEnum;

public class ParserTest {
    @Test
    public void todoCmdTest() {
        try {
            Command cmd = Parser.parse("todo  a random  !@#$%^&& task");

            Field commandField = Command.class.getDeclaredField("commandType");
            commandField.setAccessible(true);
            assertEquals(CommandEnum.TODO, commandField.get(cmd));

            Field descField = Command.class.getDeclaredField("firstParam");
            descField.setAccessible(true);
            assertEquals("a random  !@#$%^&& task", descField.get(cmd));
        } catch (Exception e) {}
    }
}
