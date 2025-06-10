package agronova.yanapay.greenhouses.domain.services.updateGreenhouse;

public interface IUpdateGreenhouseCommandHandler {
    String handle(UpdateGreenhouseCommand command);
}
