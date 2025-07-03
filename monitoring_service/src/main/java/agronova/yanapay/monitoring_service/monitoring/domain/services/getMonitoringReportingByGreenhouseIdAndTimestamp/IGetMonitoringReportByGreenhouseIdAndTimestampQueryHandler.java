package agronova.yanapay.monitoring_service.monitoring.domain.services.getMonitoringReportingByGreenhouseIdAndTimestamp;

import agronova.yanapay.monitoring_service.monitoring.domain.model.entities.MonitoringReport;

import java.util.List;

public interface IGetMonitoringReportByGreenhouseIdAndTimestampQueryHandler {
    List<MonitoringReport> handle(GetMonitoringReportByGreenhouseIdAndTimestampQuery query);
}
