package course.java.invoicing.exception;

public class RepositoryFullException extends RuntimeException {
    public RepositoryFullException() {
    }

    public RepositoryFullException(String message) {
        super(message);
    }

    public RepositoryFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryFullException(Throwable cause) {
        super(cause);
    }
}
