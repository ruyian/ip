package duke.task;


import duke.body.Parser;

public class Event extends Task {
    private String eventDateString;

    public Event(String description) {
        super();
        int indexDivider = Parser.parseSlashIndex(description);
        String eventName = description.substring(0, indexDivider).trim();
        String eventDateString = description.substring(indexDivider + 1).trim();
        this.description = eventName;
        this.eventDateString = eventDateString;
        this.taskType = TaskType.EVENT;
    }

    public String getEventDateString() {
        return eventDateString;
    }

    /**
     * Provides a String representation of event
     *
     * @return a String representation including task type, task status, task description and event date.
     */
    @Override
    public String toString() {
        return super.toString() + " (" + getEventDateString() + ")";
    }

    /**
     * Returns a formatted String that to be stored in file, duke.txt
     *
     * @return the formatted String
     */
    public String describeInFile() {
        return getDescription() + " | " + getEventDateString();
    }
}
