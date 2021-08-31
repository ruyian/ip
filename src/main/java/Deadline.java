public class Deadline extends Task {
    private String deadLineDate;

    public Deadline(String description) {
        int indexDivider = description.indexOf("/");
        String deadLineName = description.substring(0, indexDivider).trim();
        String deadLineDateString = description.substring(indexDivider + 1).trim();
        String deadLineDate = deadLineDateString.split(" ", 2)[1].trim();
        this.description = deadLineName;
        this.deadLineDate = deadLineDateString;
        this.taskType = "D";
        this.isDone = false;
    }

    public String getDeadLineDate() {
        return this.deadLineDate;
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "][" + getStatusIcon() + "] " + getDescription() +
                " (" + getDeadLineDate() + ")";
    }
}
