package duke.task;

enum TaskType {
    TO_DO, DEADLINE, EVENT;
}

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    public Task() {

    }

    abstract String describe();

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

    public void setDone(){
        this.isDone = true;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getTaskType() {
        switch (this.taskType) {
        case TO_DO:
            return "T";
        case EVENT:
            return "E";
        case DEADLINE:
            return "D";
        default:
            return "N";
        }
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "][" + getStatusIcon() + "] " + getDescription();
    }
}
