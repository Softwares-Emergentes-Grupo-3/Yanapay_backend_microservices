package agronova.yanapay.monitoring_service.monitoring.application.getMonitoringReportingByGreenhouseId;

import agronova.yanapay.monitoring_service.monitoring.domain.model.entities.MonitoringReport;
import agronova.yanapay.monitoring_service.monitoring.domain.services.getMonitoringReportingByGreenhouseId.GetMonitoringReportingByGreenhouseIdQuery;
import agronova.yanapay.monitoring_service.monitoring.domain.services.getMonitoringReportingByGreenhouseId.IGetMonitoringReportingByGreenhouseIdQueryHandler;
import agronova.yanapay.monitoring_service.monitoring.infrastructure.persistence.jpa.repositories.MonitoringReportRepository;
import agronova.yanapay.monitoring_service.shared.interfaces.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetMonitoringReportingByGreenhouseIdQueryHandler implements IGetMonitoringReportingByGreenhouseIdQueryHandler {

    private final MonitoringReportRepository repository;

    @Autowired
    public GetMonitoringReportingByGreenhouseIdQueryHandler(MonitoringReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public MonitoringReport handle(GetMonitoringReportingByGreenhouseIdQuery query) {
        var monitoringReport = repository.findTopByGreenhouseIdOrderByTimestampDesc(query.greenhouseId())
                .orElseThrow(() -> new ResourceNotFoundException("Greenhouse not found with id: " + query.greenhouseId()));

        return monitoringReport;

    }
}
