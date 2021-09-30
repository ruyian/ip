package duke.exception;

/**
 * A subclass of IrregularInputException, when an illegal input of empty input is keyyed in
 */
public class EmptyInputException extends IrregularInputException {

    public EmptyInputException() {
        super();
    }

    public EmptyInputException(String mesagge) {
        super(mesagge);
    }

}
