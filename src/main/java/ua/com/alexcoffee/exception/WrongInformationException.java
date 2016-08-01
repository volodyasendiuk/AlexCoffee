package ua.com.alexcoffee.exception;

// Exception thrown when wrong model information
public class WrongInformationException extends RuntimeException {

    public WrongInformationException() {
        super();
    }

    public WrongInformationException(String message) {
        super(message);
    }
}
