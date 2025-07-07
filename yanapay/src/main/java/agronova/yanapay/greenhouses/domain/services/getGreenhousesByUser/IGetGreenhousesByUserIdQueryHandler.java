package agronova.yanapay.greenhouses.domain.services.getGreenhousesByUser;

import agronova.yanapay.greenhouses.domain.model.aggregates.Greenhouse;

import java.util.List;

public interface IGetGreenhousesByUserIdQueryHandler {
    List<Greenhouse> handle(GetGreenhousesByUserIdQuery query);
}
