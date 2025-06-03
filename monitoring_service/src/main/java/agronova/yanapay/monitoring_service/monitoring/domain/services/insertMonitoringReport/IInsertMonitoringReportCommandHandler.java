package agronova.yanapay.monitoring_service.monitoring.domain.services.insertMonitoringReport;

public interface IInsertMonitoringReportCommandHandler {
    void handle(InsertMonitoringReportCommand command);
}
