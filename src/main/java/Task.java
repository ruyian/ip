public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task() {

    }

    public Task(String description) {
        int spaceIndex = description.indexOf(' ');
        this.description = description.substring(spaceIndex + 1);
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTaskType() {
        return this.taskType;
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "][" + getStatusIcon() + "] " + getDescription();
    }
}
