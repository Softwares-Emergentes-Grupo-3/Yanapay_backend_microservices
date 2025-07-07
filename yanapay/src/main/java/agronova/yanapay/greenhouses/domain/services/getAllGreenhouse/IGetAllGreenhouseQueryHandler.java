package agronova.yanapay.greenhouses.domain.services.getAllGreenhouse;

import agronova.yanapay.greenhouses.domain.model.aggregates.Greenhouse;

import java.util.List;


public interface IGetAllGreenhouseQueryHandler {
    List<Greenhouse> handle(GetAllGreenhouseQuery query);
}
