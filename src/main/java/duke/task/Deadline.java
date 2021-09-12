package duke.task;

public class Deadline extends Task {
    private String deadlineDate;

    public Deadline(String description) {
        int indexDivider = description.indexOf("/");
        String deadlineName = description.substring(0, indexDivider).trim();
        String deadlineDateString = description.substring(indexDivider + 1).trim();
        String deadlineDate = deadlineDateString.split(" ", 2)[1].trim();
        this.description = deadlineName;
        this.deadlineDate = deadlineDateString;
        this.taskType = "D";
        this.isDone = false;
    }

    public String getDeadLineDate() {
        return this.deadlineDate;
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "][" + getStatusIcon() + "] " + getDescription() +
                " (" + getDeadLineDate() + ")";
    }
}
