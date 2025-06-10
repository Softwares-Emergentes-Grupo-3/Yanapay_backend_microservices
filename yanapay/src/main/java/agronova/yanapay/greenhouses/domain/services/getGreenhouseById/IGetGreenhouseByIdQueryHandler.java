package agronova.yanapay.greenhouses.domain.services.getGreenhouseById;

import agronova.yanapay.monitoring.domain.model.aggregates.Device;

import java.util.Set;

public interface IGetGreenhouseByIdQueryHandler {
    Set<Device> handle(GetGreenhouseByIdQuery query);
}
