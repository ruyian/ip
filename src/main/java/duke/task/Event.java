package duke.task;

public class Event extends Task {
    private String eventDate;

    public Event(String description) {
        super();
        int indexDivider = description.indexOf("/");
        String eventName = description.substring(0, indexDivider).trim();
        int spaceIndex = description.indexOf(' ');
        String eventDateString = description.substring(indexDivider + 1).trim();
        //String eventDate = eventDateString.split(" ", 2)[1].trim();
        this.description = eventName.substring(spaceIndex + 1);
        this.eventDate = eventDateString;
        this.taskType = TaskType.EVENT;
    }

    public String getEventDate() {
        return eventDate;
    }

    @Override
    public String toString() {
        return super.toString()+ " (" + getEventDate() + ")";
    }
}
