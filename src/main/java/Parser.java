import duke.exception.DukeException;
import duke.exception.EmptyInputException;
import duke.exception.IrregularInputException;
import duke.task.Task;

public class Parser {
    public static Action parseCommand(String sentence) throws DukeException {
        if (sentence.isEmpty()) {
            throw new EmptyInputException("Empty input! Try again (o|o)\n");
        } else if (sentence.equals("bye")) {
            return Action.BYE;
        } else if (sentence.equals("list")) {
            return Action.LIST;
        } else if (sentence.startsWith("done")) {
            return Action.MARK_AS_DONE;
        } else if (sentence.startsWith("todo")) {
            return Action.CREATE_TODO;
        } else if (sentence.startsWith("deadline")) {
            return Action.CREATE_DEADLINE;
        } else if (sentence.startsWith("event")) {
            return Action.CREATE_EVENT;
        } else if (sentence.startsWith("delete")) {
            return Action.DELETE;
        } else if (sentence.startsWith("clear")) {
            return Action.CLEAR;
        } else {
            throw new IrregularInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(" +
                    "\nPlease try again!");
        }
    }

    public static int parseIndex(String sentence) {
        String[] words = sentence.split(" ");
        int targetIndex = Integer.parseInt(words[1]) - 1;
        return targetIndex;
    }
}
