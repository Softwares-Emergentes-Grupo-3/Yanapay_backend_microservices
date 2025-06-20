package agronova.yanapay.monitoring.application.internal.removeDeviceAssignment;

import agronova.yanapay.monitoring.domain.services.removeDeviceAssignment.IRemoveDeviceAssignmentCommandHandler;
import agronova.yanapay.monitoring.domain.services.removeDeviceAssignment.RemoveDeviceAssignmentCommand;
import agronova.yanapay.monitoring.infrastructure.persistence.jpa.repositories.DeviceAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveDeviceAssignmentCommandHandler implements IRemoveDeviceAssignmentCommandHandler {

    private final DeviceAssignmentRepository deviceAssignmentRepository;

    @Autowired
    public RemoveDeviceAssignmentCommandHandler(DeviceAssignmentRepository deviceAssignmentRepository) {
        this.deviceAssignmentRepository = deviceAssignmentRepository;
    }

    @Override
    public void handle(RemoveDeviceAssignmentCommand command) {
        try {
            deviceAssignmentRepository.deleteByDeviceCode(command.deviceCode());
        } catch (Exception e) {
            throw new RuntimeException("Failed to remove device assignment", e);
        }
    }
}
