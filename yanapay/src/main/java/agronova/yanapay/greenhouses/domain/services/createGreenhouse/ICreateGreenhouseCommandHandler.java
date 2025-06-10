package agronova.yanapay.greenhouses.domain.services.createGreenhouse;

public interface ICreateGreenhouseCommandHandler {
    String handle(CreateGreenhouseCommand command);
}
