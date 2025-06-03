package agronova.yanapay.monitoring.application.internal.updateDeviceConnectionStatus;

import agronova.yanapay.monitoring.domain.services.updateDeviceConnectionStatus.IUpdateDeviceConnectionStatusCommandHandler;
import agronova.yanapay.monitoring.domain.services.updateDeviceConnectionStatus.UpdateDeviceConnectionStatusCommand;
import agronova.yanapay.monitoring.infrastructure.persistence.jpa.repositories.DeviceRepository;
import agronova.yanapay.shared.interfaces.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateDeviceConnectionStatusCommandHandler implements IUpdateDeviceConnectionStatusCommandHandler {

    private final DeviceRepository deviceRepository;

    @Autowired
    public UpdateDeviceConnectionStatusCommandHandler(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public void handle(UpdateDeviceConnectionStatusCommand command) {
        var device = deviceRepository.findByDeviceCode(command.deviceCode())
                .orElseThrow(() -> new ResourceNotFoundException("Device not found with code: " + command.deviceCode()));

        device.updateConnectionStatus(command.isConnected());

        deviceRepository.save(device);
    }
}
