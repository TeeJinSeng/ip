package tasks;  //same package as the class being tested

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTest {
    @Test
    public void isOccurTest() {
        Event e = new Event("abc", LocalDateTime.of(2025, 1, 1, 0, 0), LocalDateTime.of(2025, 1, 1, 0, 1));
        assertTrue(e.isOcurringAt(LocalDateTime.of(2025, 1, 1, 0, 0, 30)));
    }
}
