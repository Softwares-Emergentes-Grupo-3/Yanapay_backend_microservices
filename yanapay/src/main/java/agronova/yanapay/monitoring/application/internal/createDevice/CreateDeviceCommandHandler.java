package agronova.yanapay.monitoring.application.internal.createDevice;

import agronova.yanapay.monitoring.domain.model.aggregates.Device;
import agronova.yanapay.monitoring.domain.services.createDevice.CreateDeviceCommand;
import agronova.yanapay.monitoring.domain.services.createDevice.ICreateDeviceCommandHandler;
import agronova.yanapay.monitoring.infrastructure.persistence.jpa.repositories.DeviceRepository;
import agronova.yanapay.shared.interfaces.exceptions.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateDeviceCommandHandler implements ICreateDeviceCommandHandler {

    private final DeviceRepository deviceRepository;

    @Autowired
    public CreateDeviceCommandHandler(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public String handle(CreateDeviceCommand command) {
        if (deviceRepository.existsByDeviceCode((command.deviceCode()))) {
            throw new ConflictException("Device code already exists");
        }

        Device device = new Device(command.deviceCode());

        deviceRepository.save(device);

        return device.getDeviceCode();
    }
}
