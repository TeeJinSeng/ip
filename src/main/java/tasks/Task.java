package tasks;

import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Boolean isOcurringAt(LocalDateTime inputDate) {
        return null;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getFormattedString() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }
}
