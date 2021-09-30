package duke.task;

import duke.body.Parser;

public class Deadline extends Task {
    private String deadlineDateString;

    public Deadline(String description) {
        super();
        int indexDivider = Parser.parseSlashIndex(description);
        String deadlineName = description.substring(0, indexDivider).trim();
        String deadlineDateString = description.substring(indexDivider + 1).trim();
        this.description = deadlineName;
        this.deadlineDateString = deadlineDateString;
        this.taskType = TaskType.DEADLINE;
    }

    public String getDeadlineDateString() {
        return deadlineDateString;
    }

    /**
     * Provides a String representation of event
     *
     * @return a String representation including task type, task status, task description and deadline date.
     */
    @Override
    public String toString() {
        return super.toString() + " (" + getDeadlineDateString() + ")";
    }


    /**
     * Returns a formatted String that to be stored in file, duke.txt
     *
     * @return the formatted String
     */
    public String describeInFile() {
        return getDescription() + " | " + getDeadlineDateString();
    }
}
