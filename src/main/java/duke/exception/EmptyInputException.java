package duke.exception;

/**
 * A subclass of IllegalInputException, when an illegal input of empty input is keyyed in
 */
public class EmptyInputException extends IllegalInputException {

    public EmptyInputException() {
        super();
    }

    public EmptyInputException(String mesagge) {
        super(mesagge);
    }

}
