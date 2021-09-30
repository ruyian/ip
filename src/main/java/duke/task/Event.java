package duke.task;

public class Event extends Task {
    private String eventDateString;

    public Event(String description) {
        super();
        int indexDivider = description.indexOf("/");
        String eventName = description.substring(0, indexDivider).trim();
        String eventDateString = description.substring(indexDivider + 1).trim();
        this.description = eventName;
        this.eventDateString = eventDateString;
        this.taskType = TaskType.EVENT;
    }

    public String getEventDateString(){
        return eventDateString;
    }

    @Override
    public String toString() {
        return super.toString()+ " (" + getEventDateString() + ")";
    }

    /** Returns a formatted String that to be stored in file, duke.txt
     * @return the formatted String
     */
    public String describeInFile() {
        return getDescription() + " | " + getEventDateString();
    }
}
