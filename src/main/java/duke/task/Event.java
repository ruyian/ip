package duke.task;

public class Event extends Task {
    private String eventDate;

    public Event(String description) {
        int indexDivider = description.indexOf("/");
        String eventName = description.substring(0, indexDivider).trim();
        String eventDateString = description.substring(indexDivider + 1).trim();
        String eventDate = eventDateString.split(" ", 2)[1].trim();
        this.description = eventName;
        this.eventDate = eventDateString;
        this.taskType = "E";
    }

    public String getEventDate() {
        return eventDate;
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "][" + getStatusIcon() + "] " + getDescription() +
                " (" + getEventDate() + ")";
    }
}
