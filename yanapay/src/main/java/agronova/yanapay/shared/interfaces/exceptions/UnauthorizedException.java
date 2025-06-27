package agronova.yanapay.shared.interfaces.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {super();}

    public UnauthorizedException(String message) {
        super(message);
    }
}
