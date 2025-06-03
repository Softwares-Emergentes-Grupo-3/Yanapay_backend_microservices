package agronova.yanapay.monitoring.application.internal.getDeviceByDeviceCode;

import agronova.yanapay.monitoring.domain.model.aggregates.Device;
import agronova.yanapay.monitoring.domain.services.getDeviceByDeviceCode.GetDeviceByDeviceCodeQuery;
import agronova.yanapay.monitoring.domain.services.getDeviceByDeviceCode.IGetDeviceByDeviceCodeQueryHandler;
import agronova.yanapay.monitoring.infrastructure.persistence.jpa.repositories.DeviceRepository;
import agronova.yanapay.shared.interfaces.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetDeviceByDeviceCodeQueryHandler implements IGetDeviceByDeviceCodeQueryHandler {

    private final DeviceRepository deviceRepository;

    @Autowired
    public GetDeviceByDeviceCodeQueryHandler(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Device handle(GetDeviceByDeviceCodeQuery query) {
        return deviceRepository.findByDeviceCode(query.deviceCode())
                .orElseThrow(() -> new ResourceNotFoundException("Device not found with code: " + query.deviceCode()));
    }
}
