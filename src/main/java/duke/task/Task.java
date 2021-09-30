package duke.task;

import duke.exception.RepeatedCompletionException;

enum TaskType {
    TO_DO, DEADLINE, EVENT;
}


/**
 *  Parent class for all type of Tasks (Todo, Event, Deadline)
 *  Contains basic methods and fields that are applicable to all subclass tasks.
 */
public abstract class Task {
    /**
     * Description of the tasks (not including the date of deadline / event)
     */
    protected String description;
    /**
     * Status of the Task, to indicate whether it is done or not
     */
    protected boolean isDone;
    /**
     * An enum that indicates which type of task it is (TODO, EVENT, DEADLINE)
     */
    protected TaskType taskType;

    public Task() {
        this.isDone = false;
    }

    /**
     * Provides a String representation of the task to be written in the text file
     *
     * @return A line of String representation of the task
     */
    public abstract String describeInFile();

    public Task(String description) {
        this();
        this.description = description;
    }

    /**
     * Marks a done task with X and an undone task with a space
     *
     * @return "X" if done, " " if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getDone() {
        return this.isDone;
    }

    /**
     * Marks a target task as done
     *
     * @throws RepeatedCompletionException if the task has already been completed
     */
    public void markAsDone() throws RepeatedCompletionException {
        if (this.getDone()) {
            throw new RepeatedCompletionException("This task has already been completed!\n");
        }
        this.isDone = true;
    }

    /**
     * Gets the specific task type of a task
     *
     * @return the specific task type of a task
     */
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

    /**
     * Provides a String representation of task
     *
     * @return a String representation including task type, task status and task description
     */
    @Override
    public String toString() {
        return "[" + getTaskType() + "][" + getStatusIcon() + "] " + getDescription();
    }

}
