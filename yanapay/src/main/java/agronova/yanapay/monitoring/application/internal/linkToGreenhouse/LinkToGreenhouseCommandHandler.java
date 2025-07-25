package agronova.yanapay.monitoring.application.internal.linkToGreenhouse;

import agronova.yanapay.greenhouses.infrastructure.infrastructure.persistence.jpa.repositories.GreenhouseRepository;
import agronova.yanapay.monitoring.domain.services.insertDeviceAssignment.IInsertDeviceAssignmentCommandHandler;
import agronova.yanapay.monitoring.domain.services.insertDeviceAssignment.InsertDeviceAssignmentCommand;
import agronova.yanapay.monitoring.domain.services.linkToGreenhouse.ILinkToGreenhouseCommandHandler;
import agronova.yanapay.monitoring.domain.services.linkToGreenhouse.LinkToGreenhouseCommand;
import agronova.yanapay.monitoring.infrastructure.persistence.jpa.repositories.DeviceRepository;
import agronova.yanapay.shared.interfaces.exceptions.ConflictException;
import agronova.yanapay.shared.interfaces.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LinkToGreenhouseCommandHandler implements ILinkToGreenhouseCommandHandler {

    private final DeviceRepository deviceRepository;
    private final GreenhouseRepository greenhouseRepository;
    private final IInsertDeviceAssignmentCommandHandler insertDeviceAssignmentCommandHandler;

    @Autowired
    public LinkToGreenhouseCommandHandler(DeviceRepository deviceRepository, GreenhouseRepository greenhouseRepository, IInsertDeviceAssignmentCommandHandler insertDeviceAssignmentCommandHandler) {
        this.deviceRepository = deviceRepository;
        this.greenhouseRepository = greenhouseRepository;
        this.insertDeviceAssignmentCommandHandler = insertDeviceAssignmentCommandHandler;
    }

    @Override
    public String handle(LinkToGreenhouseCommand command) {

        // Verify if the device exists and is Online
        var device = deviceRepository.findByDeviceCode(command.deviceCode())
                .orElseThrow(() -> new ResourceNotFoundException("Device not found: " + command.deviceCode()));

        if (!device.isOnline()) {
            throw new ConflictException("Device is not online: " + command.deviceCode());
        }

        // Verify if the device is already linked to a greenhouse
        if (device.getGreenhouse() != null) {
            throw new ConflictException("Device is already linked to a greenhouse: " + command.deviceCode());
        }

        // Verify if the greenhouse exists
        var greenhouse = greenhouseRepository.findById(command.greenhouseId())
                .orElseThrow(() -> new ResourceNotFoundException("Greenhouse not found: " + command.greenhouseId()));


        // Link the device to the greenhouse
        device.linkToGreenhouse(greenhouse);

        // Save the device
        deviceRepository.save(device);

        // Insert a monitoring report to indicate the device is linked to the greenhouse
        var deviceAssignmentCommand = new InsertDeviceAssignmentCommand(device.getDeviceCode(), greenhouse.getId());

        insertDeviceAssignmentCommandHandler.handle(deviceAssignmentCommand);

        return "Device linked to greenhouse successfully: " + command.deviceCode();
    }
}
