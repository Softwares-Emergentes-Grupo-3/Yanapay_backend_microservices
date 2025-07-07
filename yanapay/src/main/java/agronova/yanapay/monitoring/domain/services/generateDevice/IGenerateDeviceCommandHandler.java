package agronova.yanapay.monitoring.domain.services.generateDevice;

public interface IGenerateDeviceCommandHandler {
    String handle(GenerateDeviceCommand command);
}
