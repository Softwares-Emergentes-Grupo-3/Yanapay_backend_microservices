package agronova.yanapay.monitoring_service.monitoring.application;

import agronova.yanapay.monitoring_service.monitoring.domain.model.entities.MonitoringReport;
import agronova.yanapay.monitoring_service.monitoring.domain.services.insertMonitoringReport.IInsertMonitoringReportCommandHandler;
import agronova.yanapay.monitoring_service.monitoring.domain.services.insertMonitoringReport.InsertMonitoringReportCommand;
import agronova.yanapay.monitoring_service.monitoring.infrastructure.persistence.jpa.repositories.DeviceAssignmentRepository;
import agronova.yanapay.monitoring_service.monitoring.infrastructure.persistence.jpa.repositories.MonitoringReportRepository;
import agronova.yanapay.monitoring_service.shared.interfaces.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InsertMonitoringReportCommandHandler implements IInsertMonitoringReportCommandHandler {

    private final MonitoringReportRepository repository;
    private final DeviceAssignmentRepository assignmentRepository;
    private static final Logger logger = LoggerFactory.getLogger(InsertMonitoringReportCommandHandler.class);

    @Autowired
    public InsertMonitoringReportCommandHandler(MonitoringReportRepository repository, DeviceAssignmentRepository assignmentRepository) {
        this.repository = repository;
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public void handle(InsertMonitoringReportCommand command) {

        try {
            var report = MonitoringReport.fromInsertMonitoringReportCommand(command);

            var deviceAssignment = assignmentRepository.findByDeviceCode((command.deviceCode()))
                    .orElseThrow(() -> new ResourceNotFoundException("Device assignment not found for device: " + command.deviceCode()));

            report.setGreenhouseId(deviceAssignment.getGreenhouseId());

            repository.save(report);

            logger.info("Monitoring report inserted successfully for device: {}", command.deviceCode());
        }
        catch (RuntimeException e) {
            logger.error("Error: {}", e.getMessage());
        }
        catch (Exception e) {
            logger.error("An internal error occurred: {}", e.getMessage());
        }
    }
}
