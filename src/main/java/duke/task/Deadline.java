package duke.task;

public class Deadline extends Task {
    private String deadlineDate;

    public Deadline(String description) {
        //super();
        int indexDivider = description.indexOf("/");
        int spaceIndex = description.indexOf(' ');
        String deadlineName = description.substring(0, indexDivider).trim();
        String deadlineDateString = description.substring(indexDivider + 1).trim();
        //String deadlineDate = deadlineDateString.split(" ", 2)[1].trim();
        this.deadlineDate = deadlineDateString;
        this.description = deadlineName.substring(spaceIndex + 1);
        this.taskType = TaskType.DEADLINE;
    }

    public String getDeadLineDate() {
        return this.deadlineDate;
    }

    @Override
    public String toString() {
        return super.toString() + " (" + getDeadLineDate() + ")";
    }
}
