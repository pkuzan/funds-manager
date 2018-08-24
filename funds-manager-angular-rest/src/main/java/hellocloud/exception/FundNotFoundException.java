package hellocloud.exception;

public class FundNotFoundException extends RuntimeException {
    public FundNotFoundException(String message) {
        super(message);
    }
}
