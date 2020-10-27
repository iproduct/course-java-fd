package course.java.invoicing.exception;

public class ActionUnsuccessfulException extends Exception {
    public ActionUnsuccessfulException() {
    }

    public ActionUnsuccessfulException(String message) {
        super(message);
    }

    public ActionUnsuccessfulException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionUnsuccessfulException(Throwable cause) {
        super(cause);
    }
}
