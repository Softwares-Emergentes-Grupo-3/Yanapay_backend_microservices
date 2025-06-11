package agronova.yanapay.greenhouses.application.internal.getGreenhouseById;

import agronova.yanapay.greenhouses.domain.model.aggregates.Greenhouse;
import agronova.yanapay.greenhouses.domain.model.infrastructure.persistence.jpa.repositories.GreenhouseRepository;
import agronova.yanapay.greenhouses.domain.services.getGreenhouseById.GetGreenhouseByIdQuery;
import agronova.yanapay.greenhouses.domain.services.getGreenhouseById.IGetGreenhouseByIdQueryHandler;
import agronova.yanapay.monitoring.domain.model.aggregates.Device;
import agronova.yanapay.monitoring.infrastructure.persistence.jpa.repositories.DeviceRepository;
import agronova.yanapay.shared.interfaces.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GetGreenhouseByIdQueryHandler implements IGetGreenhouseByIdQueryHandler {

    private final GreenhouseRepository greenhouseRepository;
    private final DeviceRepository deviceRepository;

    @Autowired
    public GetGreenhouseByIdQueryHandler(GreenhouseRepository greenhouseRepository, DeviceRepository deviceRepository) {
        this.greenhouseRepository = greenhouseRepository;
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Greenhouse handle(GetGreenhouseByIdQuery query) {
            var greenhouse = greenhouseRepository.findById(query.id())
                    .orElseThrow(() -> new ResourceNotFoundException("Greenhouse not found with id: " + query.id()));
            Set<Device> devices = deviceRepository.findAllByGreenhouse_Id(query.id());

            greenhouse.setDevices(devices);
            return greenhouse;

    }

}
