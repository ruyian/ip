package duke.exception;

/**
 * An subclass of exception that is thrown when illegal input is keyyed in
 */
public class IrregularInputException extends DukeException {
    public IrregularInputException() {
        super();
    }

    public IrregularInputException(String message) {
        super(message);
    }
}
