package agronova.yanapay.greenhouses.domain.services.updateGreenhouse;

import agronova.yanapay.greenhouses.domain.model.aggregates.Greenhouse;

public interface IUpdateGreenhouseCommandHandler {
    Greenhouse handle(UpdateGreenhouseCommand command);
}
