package agronova.yanapay.monitoring_service.monitoring.domain.services.insertMonitoringReport;

public record InsertMonitoringReportCommand(String deviceCode, float temperature, float humidity) {
}
