package agronova.yanapay.monitoring.application.internal.unlinkToGreenhouse;

import agronova.yanapay.monitoring.domain.services.removeDeviceAssignment.IRemoveDeviceAssignmentCommandHandler;
import agronova.yanapay.monitoring.domain.services.removeDeviceAssignment.RemoveDeviceAssignmentCommand;
import agronova.yanapay.monitoring.domain.services.unlinkToGreenhouse.IUnlinkToGreenhouseCommandHandler;
import agronova.yanapay.monitoring.domain.services.unlinkToGreenhouse.UnlinkToGreenhouseCommand;
import agronova.yanapay.monitoring.infrastructure.persistence.jpa.repositories.DeviceRepository;
import agronova.yanapay.shared.interfaces.exceptions.ConflictException;
import agronova.yanapay.shared.interfaces.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnlinkToGreenhouseCommandHandler implements IUnlinkToGreenhouseCommandHandler {

    private final DeviceRepository deviceRepository;
    private final IRemoveDeviceAssignmentCommandHandler removeDeviceAssignmentCommandHandler;

    @Autowired
    public UnlinkToGreenhouseCommandHandler(DeviceRepository deviceRepository, IRemoveDeviceAssignmentCommandHandler removeDeviceAssignmentCommandHandler) {
        this.deviceRepository = deviceRepository;
        this.removeDeviceAssignmentCommandHandler = removeDeviceAssignmentCommandHandler;
    }

    @Override
    public String handle(UnlinkToGreenhouseCommand command) {
        // Verify if the device exists
        var device = deviceRepository.findByDeviceCode(command.deviceCode())
                .orElseThrow(() -> new ResourceNotFoundException("Device not found: " + command.deviceCode()));

        // Verify if the device is linked to a greenhouse
        if (device.getGreenhouse() == null) {
            throw new ConflictException("Device is not linked to any greenhouse: " + command.deviceCode());
        }

        // Unlink the device from the greenhouse
        device.unlinkFromGreenhouse();

        // Save the device
        deviceRepository.save(device);

        removeDeviceAssignmentCommandHandler.handle(new RemoveDeviceAssignmentCommand(device.getDeviceCode()));

        return "Device unlinked from greenhouse successfully: " + command.deviceCode();
    }
}
