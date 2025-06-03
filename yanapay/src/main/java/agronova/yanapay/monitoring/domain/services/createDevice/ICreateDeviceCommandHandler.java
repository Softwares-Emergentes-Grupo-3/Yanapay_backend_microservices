package agronova.yanapay.monitoring.domain.services.createDevice;

public interface ICreateDeviceCommandHandler {
    String handle(CreateDeviceCommand command);
}
