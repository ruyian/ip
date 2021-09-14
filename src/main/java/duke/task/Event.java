package duke.task;

public class Event extends Task {
    //private String eventDate;
    private String eventDateString;

    public Event(String description) {
        int indexDivider = description.indexOf("/");
        String eventName = description.substring(0, indexDivider).trim();
        String eventDateString = description.substring(indexDivider + 1).trim();
        //String eventDate = eventDateString.split(" ", 2)[1].trim();
        this.description = eventName;
        this.eventDateString = eventDateString;
        this.taskType = TaskType.EVENT;
    }

    public String getEventDateString(){
        return eventDateString;
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "][" + getStatusIcon() + "] " + getDescription() +
                " (" + getEventDateString() + ")";
    }

    // for the use of exporting into a text
    public String describe() {
        return getDescription() + " | " + getEventDateString();
    }
}
