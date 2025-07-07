package agronova.yanapay.monitoring.domain.services.getAllDevices;

import agronova.yanapay.monitoring.domain.model.aggregates.Device;

import java.util.List;

public interface IGetAllDevicesQueryHandler {
    List<Device> handle(GetAllDevicesQuery query);
}
