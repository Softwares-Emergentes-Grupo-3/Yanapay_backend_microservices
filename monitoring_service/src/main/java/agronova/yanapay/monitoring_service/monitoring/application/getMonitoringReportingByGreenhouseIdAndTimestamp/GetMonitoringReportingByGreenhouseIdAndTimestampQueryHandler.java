package agronova.yanapay.monitoring_service.monitoring.application.getMonitoringReportingByGreenhouseIdAndTimestamp;

import agronova.yanapay.monitoring_service.monitoring.domain.model.entities.MonitoringReport;
import agronova.yanapay.monitoring_service.monitoring.domain.services.getMonitoringReportingByGreenhouseIdAndTimestamp.GetMonitoringReportByGreenhouseIdAndTimestampQuery;
import agronova.yanapay.monitoring_service.monitoring.domain.services.getMonitoringReportingByGreenhouseIdAndTimestamp.IGetMonitoringReportByGreenhouseIdAndTimestampQueryHandler;
import agronova.yanapay.monitoring_service.monitoring.infrastructure.persistence.jpa.repositories.MonitoringReportRepository;
import agronova.yanapay.monitoring_service.shared.interfaces.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GetMonitoringReportingByGreenhouseIdAndTimestampQueryHandler implements IGetMonitoringReportByGreenhouseIdAndTimestampQueryHandler {
    private final MonitoringReportRepository repository;

    @Autowired
    public GetMonitoringReportingByGreenhouseIdAndTimestampQueryHandler(MonitoringReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<MonitoringReport> handle(GetMonitoringReportByGreenhouseIdAndTimestampQuery query) {

        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        List<MonitoringReport> listMonitoringReport = repository.findByGreenhouseIdAndTimestampAfterOrderByTimestampAsc(
                query.greenhouseid(), sevenDaysAgo);

        if (listMonitoringReport.isEmpty()) {
            throw new ResourceNotFoundException("No records were found for the greenhouse with id: " + query.greenhouseid());
        }

        return listMonitoringReport;
    }
}
