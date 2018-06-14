package backend.exception;

public class IncorrectParamsException extends Exception {
    public IncorrectParamsException(String message) {
        super(message);
    }

    public IncorrectParamsException(String message, Throwable cause) {
        super(message, cause);

    }
}
