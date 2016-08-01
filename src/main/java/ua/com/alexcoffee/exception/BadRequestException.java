package ua.com.alexcoffee.exception;

// Exception thrown when data not found in database
public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }
}
