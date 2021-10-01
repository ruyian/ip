package duke.exception;

/**
 * An subclass of exception that is thrown when illegal input is keyyed in
 */
public class IllegalInputException extends DukeException {
    public IllegalInputException() {
        super();
    }

    public IllegalInputException(String message) {
        super(message);
    }
}
