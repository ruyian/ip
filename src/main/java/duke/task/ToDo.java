package duke.task;

public class ToDo extends Task {

    /**
     * creates a todo with description with users input
     *
     * @param description - users' input
     */
    public ToDo(String description) {
        super(description);
        this.taskType = TaskType.TO_DO;
    }

    /**
     * Returns a formatted String that to be stored in file, duke.txt
     *
     * @return the formatted String
     */
    public String describeInFile() {
        return getDescription();
    }
}
