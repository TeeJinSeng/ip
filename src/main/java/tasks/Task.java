package tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAs(boolean b) {
        this.isDone = b;
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
