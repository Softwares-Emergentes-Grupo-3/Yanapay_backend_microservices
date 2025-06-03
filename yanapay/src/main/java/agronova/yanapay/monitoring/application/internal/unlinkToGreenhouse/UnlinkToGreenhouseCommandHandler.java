package agronova.yanapay.monitoring.application.internal.unlinkToGreenhouse;

import agronova.yanapay.monitoring.domain.services.removeMonitoringReportCache.IRemoveMonitoringReportCacheCommandHandler;
import agronova.yanapay.monitoring.domain.services.removeMonitoringReportCache.RemoveMonitoringReportCacheCommand;
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
    private final IRemoveMonitoringReportCacheCommandHandler removeMonitoringReportCacheCommandHandler;

    @Autowired
    public UnlinkToGreenhouseCommandHandler(DeviceRepository deviceRepository, IRemoveMonitoringReportCacheCommandHandler removeMonitoringReportCacheCommandHandler) {
        this.deviceRepository = deviceRepository;
        this.removeMonitoringReportCacheCommandHandler = removeMonitoringReportCacheCommandHandler;
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

        // Remove the monitoring report cache for the device
        removeMonitoringReportCacheCommandHandler.handle(new RemoveMonitoringReportCacheCommand(device.getDeviceCode()));

        return "Device unlinked from greenhouse successfully: " + command.deviceCode();
    }
}
