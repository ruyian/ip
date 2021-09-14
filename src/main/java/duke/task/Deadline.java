package duke.task;

public class Deadline extends Task {
    //private String deadlineDate;
    private String deadlineDateString;

    public Deadline(String description) {
        int indexDivider = description.indexOf("/");
        String deadlineName = description.substring(0, indexDivider).trim();
        String deadlineDateString = description.substring(indexDivider + 1).trim();
        //String deadlineDate = deadlineDateString.split(" ", 2)[1].trim();
        this.description = deadlineName;
        this.deadlineDateString = deadlineDateString;
        //this.deadlineDate = deadlineDate;
        this.taskType = TaskType.DEADLINE;
        this.isDone = false;
    }


    public String getDeadlineDateString() {
        return deadlineDateString;
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "][" + getStatusIcon() + "] " + getDescription() +
                " (" + getDeadlineDateString() + ")";
    }

    public String describe() {
        return getDescription() + " | " + getDeadlineDateString();
    }
}
