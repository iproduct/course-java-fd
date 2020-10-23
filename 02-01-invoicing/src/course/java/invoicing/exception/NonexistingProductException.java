package course.java.invoicing.exception;

public class NonexistingProductException extends Exception {
    public NonexistingProductException() {
    }

    public NonexistingProductException(String message) {
        super(message);
    }

    public NonexistingProductException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonexistingProductException(Throwable cause) {
        super(cause);
    }
}
