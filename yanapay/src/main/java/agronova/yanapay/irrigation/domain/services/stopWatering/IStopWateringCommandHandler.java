package agronova.yanapay.irrigation.domain.services.stopWatering;

public interface IStopWateringCommandHandler {
    String handle(StopWateringCommand command);
}
