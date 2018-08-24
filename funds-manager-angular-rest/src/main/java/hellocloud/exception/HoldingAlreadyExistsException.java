package hellocloud.exception;

public class HoldingAlreadyExistsException extends RuntimeException {
    public HoldingAlreadyExistsException(String message) {
        super(message);
    }
}
