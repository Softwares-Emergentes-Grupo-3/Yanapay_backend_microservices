package agronova.yanapay.greenhouses.application.internal.getDevicesByGreenhouseId;

import agronova.yanapay.greenhouses.domain.services.getDevicesByGreenhouseId.GetDevicesByGreenhouseIdQuery;
import agronova.yanapay.greenhouses.domain.services.getDevicesByGreenhouseId.IGetDevicesByGreenhouseIdQueryHandler;
import agronova.yanapay.greenhouses.infrastructure.infrastructure.persistence.jpa.repositories.GreenhouseRepository;
import agronova.yanapay.monitoring.domain.model.aggregates.Device;
import agronova.yanapay.monitoring.infrastructure.persistence.jpa.repositories.DeviceRepository;
import agronova.yanapay.shared.interfaces.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GetDevicesByGreenhouseIdQueryHandler implements IGetDevicesByGreenhouseIdQueryHandler {

    private final DeviceRepository deviceRepository;
    private final GreenhouseRepository greenhouseRepository;

    public GetDevicesByGreenhouseIdQueryHandler(DeviceRepository deviceRepository, GreenhouseRepository greenhouseRepository) {
        this.deviceRepository = deviceRepository;
        this.greenhouseRepository = greenhouseRepository;
    }

    @Override
    public Set<Device> handle(GetDevicesByGreenhouseIdQuery query) {

        if (!greenhouseRepository.existsGreenhouseById(query.greenhouseId())) {
            throw new ResourceNotFoundException("Greenhouse not found with id: " + query.greenhouseId());
        }

        var devices = deviceRepository.findAllByGreenhouse_Id(query.greenhouseId());

        if (devices.isEmpty()) {
            throw new ResourceNotFoundException("No devices found for greenhouse with id: " + query.greenhouseId());
        }

        return devices;
    }
}
