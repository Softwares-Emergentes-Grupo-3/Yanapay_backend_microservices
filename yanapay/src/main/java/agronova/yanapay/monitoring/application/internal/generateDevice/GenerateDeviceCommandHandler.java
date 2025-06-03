package agronova.yanapay.monitoring.application.internal.generateDevice;

import agronova.yanapay.monitoring.domain.model.aggregates.Device;
import agronova.yanapay.monitoring.domain.services.generateDevice.GenerateDeviceCommand;
import agronova.yanapay.monitoring.domain.services.generateDevice.IGenerateDeviceCommandHandler;
import agronova.yanapay.monitoring.infrastructure.persistence.jpa.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerateDeviceCommandHandler implements IGenerateDeviceCommandHandler {
    private final DeviceRepository deviceRepository;

    @Autowired
    public GenerateDeviceCommandHandler(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public String handle(GenerateDeviceCommand command) {
        Device device = new Device();

        deviceRepository.save(device);

        return device.getDeviceCode();
    }
}
