package agronova.yanapay.monitoring.domain.model.factories;

import agronova.yanapay.monitoring.domain.model.entities.DeviceAssignment;
import agronova.yanapay.monitoring.domain.services.insertDeviceAssignment.InsertDeviceAssignmentCommand;

public class DeviceAssignmentFactory {
    public static DeviceAssignment createDeviceAssignmentFromInsertDeviceAssignmentCommand(InsertDeviceAssignmentCommand command) {
        return new DeviceAssignment(
            command.deviceCode(),
            command.greenhouseId()
        );
    }
}
