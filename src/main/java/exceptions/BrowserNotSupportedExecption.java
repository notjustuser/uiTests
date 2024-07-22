package exceptions;

public class BrowserNotSupportedExecption extends RuntimeException {
    public BrowserNotSupportedExecption(String browserName) {
        super(String.format("Browser %s is not supported", browserName));
    }
}
