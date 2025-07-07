package agronova.yanapay.monitoring_service.monitoring.domain.services.getMonitoringReportingByGreenhouseId;

import agronova.yanapay.monitoring_service.monitoring.domain.model.entities.MonitoringReport;


public interface IGetMonitoringReportingByGreenhouseIdQueryHandler {
    MonitoringReport handle(GetMonitoringReportingByGreenhouseIdQuery query);
}
