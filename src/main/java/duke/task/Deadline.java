package duke.task;

public class Deadline extends Task {
    //private String deadlineDate;
    private String deadlineDateString;
    private String deadlineDate;

    public Deadline(String description) {

        super();
        int indexDivider = description.indexOf("/");
        String eventName = description.substring(0, indexDivider).trim();
        String eventDateString = description.substring(indexDivider + 1).trim();
        //String eventDate = eventDateString.split(" ", 2)[1].trim();
        this.description = eventName;
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

    public String describe() {
        return getDescription() + " | " + getDeadlineDateString();
    }
}
