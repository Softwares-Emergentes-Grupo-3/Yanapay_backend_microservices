package agronova.yanapay.monitoring.application.internal.getAllDevices;

import agronova.yanapay.monitoring.domain.model.aggregates.Device;
import agronova.yanapay.monitoring.domain.services.getAllDevices.GetAllDevicesQuery;
import agronova.yanapay.monitoring.domain.services.getAllDevices.IGetAllDevicesQueryHandler;
import agronova.yanapay.monitoring.infrastructure.persistence.jpa.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllDevicesQueryHandler implements IGetAllDevicesQueryHandler {

    private final DeviceRepository deviceRepository;

    @Autowired
    public GetAllDevicesQueryHandler(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public List<Device> handle(GetAllDevicesQuery query) {
        return deviceRepository.findAll();
    }
}
