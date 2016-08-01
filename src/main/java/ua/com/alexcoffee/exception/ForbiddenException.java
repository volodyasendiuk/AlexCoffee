package ua.com.alexcoffee.exception;

// Exception thrown when user do not have sufficient permissions to access this page.
public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String message) {
        super(message);
    }
}
