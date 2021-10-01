package duke.task;

public class Deadline extends Task {
    private String deadlineDateString;

    public Deadline(String description) {
        super();
        int indexDivider = description.indexOf("/");
        String deadlineName = description.substring(0, indexDivider).trim();
        String deadlineDateString = description.substring(indexDivider + 1).trim();
        this.description = deadlineName;
        this.deadlineDateString = deadlineDateString;
        this.taskType = TaskType.DEADLINE;
    }


    public String getDeadlineDateString() {
        return deadlineDateString;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + getDeadlineDateString() + ")";
    }

    // for the use of exporting into a text
    public String describe() {
        return getDescription() + " | " + getDeadlineDateString();
    }
}
