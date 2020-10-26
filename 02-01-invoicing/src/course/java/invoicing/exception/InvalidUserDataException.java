package course.java.invoicing.exception;

public class InvalidUserDataException extends Exception {
    public InvalidUserDataException() {
    }

    public InvalidUserDataException(String message) {
        super(message);
    }

    public InvalidUserDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUserDataException(Throwable cause) {
        super(cause);
    }
}
