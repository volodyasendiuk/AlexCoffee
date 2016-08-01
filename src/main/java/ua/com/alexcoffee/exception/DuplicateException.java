package ua.com.alexcoffee.exception;

// Exception thrown duplicate data in database
public class DuplicateException extends RuntimeException {

    public DuplicateException() {
        super();
    }

    public DuplicateException(String message) {
        super(message);
    }
}
