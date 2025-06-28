package agronova.yanapay.greenhouses.domain.services.getDevicesByGreenhouseId;

import agronova.yanapay.monitoring.domain.model.aggregates.Device;

import java.util.Set;

public interface IGetDevicesByGreenhouseIdQueryHandler {
    Set<Device> handle(GetDevicesByGreenhouseIdQuery query);
}
