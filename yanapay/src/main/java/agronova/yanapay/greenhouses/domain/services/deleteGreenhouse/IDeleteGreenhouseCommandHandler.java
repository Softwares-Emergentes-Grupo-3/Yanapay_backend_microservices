package agronova.yanapay.greenhouses.domain.services.deleteGreenhouse;

public interface IDeleteGreenhouseCommandHandler {
    String handle(DeleteGreenhouseCommand command);
}
