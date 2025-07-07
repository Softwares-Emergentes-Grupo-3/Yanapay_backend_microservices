package agronova.yanapay.greenhouses.domain.services.createGreenhouse;

import agronova.yanapay.greenhouses.domain.model.aggregates.Greenhouse;

public interface ICreateGreenhouseCommandHandler {
    Greenhouse handle(CreateGreenhouseCommand command);
}
