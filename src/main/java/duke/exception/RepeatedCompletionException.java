package duke.exception;

public class RepeatedCompletionException extends DukeException{

    public RepeatedCompletionException(){
        super();
    }

    public RepeatedCompletionException(String message){
        super(message);
    }
}
