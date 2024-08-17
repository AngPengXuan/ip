public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void toggleIsDone() {
        this.isDone = !this.isDone;
    }

    @Override
    public String toString() {
        return this.description;
    }
}
