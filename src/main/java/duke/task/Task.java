package duke.task;

import duke.exception.RepeatedCompletionException;

enum TaskType {
    TO_DO, DEADLINE, EVENT;
}


public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    public Task() {
        this.isDone = false;
    }

    public abstract String describe();

    public Task(String description) {
        this();
        int spaceIndex = description.indexOf(' ');
        this.description = description.substring(spaceIndex + 1);
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

    public void markAsDone() throws RepeatedCompletionException {
        if (this.getDone()) {
            throw new RepeatedCompletionException("This task has already been completed!\n");
        }
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
            return "N"; // stands for unknown tasks
        }
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "][" + getStatusIcon() + "] " + getDescription();
    }

}
