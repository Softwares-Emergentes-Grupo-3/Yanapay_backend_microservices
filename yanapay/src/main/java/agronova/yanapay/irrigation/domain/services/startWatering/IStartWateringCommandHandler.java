package agronova.yanapay.irrigation.domain.services.startWatering;

public interface IStartWateringCommandHandler {
    String handle(StartWateringCommand command);
}
