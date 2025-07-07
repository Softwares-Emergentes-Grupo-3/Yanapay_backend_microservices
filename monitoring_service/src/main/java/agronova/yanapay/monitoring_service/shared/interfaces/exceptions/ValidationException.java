package agronova.yanapay.monitoring_service.shared.interfaces.exceptions;

public class ValidationException extends RuntimeException{
    public ValidationException(){
        super();
    }

    public ValidationException(String message){
        super(message);
    }
}
