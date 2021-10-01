package duke.body;

import duke.exception.DukeException;
import duke.exception.EmptyInputException;
import duke.exception.IllegalInputException;

public class Parser {
    /**
     * Parses user input to corresponding command enum based on user input.
     *
     * @param sentence Raw String taken from user input
     * @return an action to be taken by Command
     * @throws DukeException including illegal inputs and empty inputs
     */
    public static Action parseCommand(String sentence) throws DukeException {
        if (sentence.isEmpty()) {
            throw new EmptyInputException("Empty input! Try again (o|o)");
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
        } else if (sentence.startsWith("find")) {
            return Action.FIND;
        } else {
            throw new IllegalInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-( \nPlease try again!");
        }
    }

    /**
     * Parse the index of the tasks the user wants to done / delete
     *
     * @param sentence the raw String from user input of "done" and "delete"
     * @return targetIndex,
     * @throws IllegalInputException if an int cannot be parsed
     */
    public static int parseIndex(String sentence) throws IllegalInputException {
        String[] words = sentence.split(" ");
        try {
            int targetIndex = Integer.parseInt(words[1]) - 1;
            return targetIndex;
        } catch (NumberFormatException nfe) {
            throw new IllegalInputException("Not a number. Try again!");
        }
    }

    /**
     * Parse the keyword of the user looks for
     *
     * @param sentence, raw user input
     * @return the keyword that the user looks for
     * @throws IllegalInputException if bad keyword or no keyword is keyyed in
     */
    public static String parseKeyWord(String sentence) throws IllegalInputException {
        try {
            int spaceIndex = sentence.indexOf(' ');
            if (spaceIndex == -1) {
                throw new IndexOutOfBoundsException();
            }
            String keyWord = sentence.substring(spaceIndex + 1);
            return keyWord;
        } catch (NumberFormatException nfe) {
            throw new IllegalInputException("Bad keyword!");
        } catch (IndexOutOfBoundsException ie) {
            throw new IllegalInputException("You have not keyed in any keyword");
        }
    }

    /**
     * Parse the description of task from user input
     *
     * @param sentence raw user input
     * @return the description of the task
     */
    public static String parseDescription(String sentence) {
        int spaceIndex = sentence.indexOf(' ');
        String description = sentence.substring(spaceIndex + 1);
        return description;
    }

    /**
     * Parse the "/" used in event and deadline input that separates the description
     * and date
     *
     * @param sentence - raw user input
     * @return the index at which "/" is found
     */
    public static int parseSlashIndex(String sentence) {
        return sentence.indexOf("/");
    }
}
