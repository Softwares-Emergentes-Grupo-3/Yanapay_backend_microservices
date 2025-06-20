package agronova.yanapay.monitoring.application.internal.insertDeviceAssignment;

import agronova.yanapay.monitoring.domain.model.factories.DeviceAssignmentFactory;
import agronova.yanapay.monitoring.domain.services.insertDeviceAssignment.IInsertDeviceAssignmentCommandHandler;
import agronova.yanapay.monitoring.domain.services.insertDeviceAssignment.InsertDeviceAssignmentCommand;
import agronova.yanapay.monitoring.infrastructure.persistence.jpa.repositories.DeviceAssignmentRepository;
import org.springframework.stereotype.Service;

@Service
public class InsertDeviceAssignmentCommandHandler implements IInsertDeviceAssignmentCommandHandler {

    private final DeviceAssignmentRepository deviceAssignmentRepository;

    public InsertDeviceAssignmentCommandHandler(DeviceAssignmentRepository deviceAssignmentRepository) {
        this.deviceAssignmentRepository = deviceAssignmentRepository;
    }

    @Override
    public void handle(InsertDeviceAssignmentCommand command) {
        try {
            var deviceAssignment = DeviceAssignmentFactory
                .createDeviceAssignmentFromInsertDeviceAssignmentCommand(command);

            deviceAssignmentRepository.save(deviceAssignment);
        } catch (Exception e) {
            throw new RuntimeException("Failed to insert device assignment", e);
        }

    }
}
