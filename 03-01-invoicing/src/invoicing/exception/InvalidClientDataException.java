package invoicing.exception;

public class InvalidClientDataException extends Exception {
    private Object invalidEntity;

    public InvalidClientDataException() {
    }

    public InvalidClientDataException(String message) {
        super(message);
    }

    public InvalidClientDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidClientDataException(Throwable cause) {
        super(cause);
    }

    public InvalidClientDataException(Object invalidEntity) {
        this.invalidEntity = invalidEntity;
    }

    public InvalidClientDataException(String message, Object invalidEntity) {
        super(message);
        this.invalidEntity = invalidEntity;
    }

    public InvalidClientDataException(String message, Throwable cause, Object invalidEntity) {
        super(message, cause);
        this.invalidEntity = invalidEntity;
    }

    public InvalidClientDataException(Throwable cause, Object invalidEntity) {
        super(cause);
        this.invalidEntity = invalidEntity;
    }

    public Object getInvalidEntity() {
        return invalidEntity;
    }
}
