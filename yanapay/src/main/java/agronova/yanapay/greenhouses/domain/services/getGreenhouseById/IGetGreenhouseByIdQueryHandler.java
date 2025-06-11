package agronova.yanapay.greenhouses.domain.services.getGreenhouseById;

import agronova.yanapay.greenhouses.domain.model.aggregates.Greenhouse;

public interface IGetGreenhouseByIdQueryHandler {
    Greenhouse handle(GetGreenhouseByIdQuery query);
}
