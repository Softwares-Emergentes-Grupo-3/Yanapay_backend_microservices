package agronova.yanapay.monitoring_service.monitoring.domain.services.insertMonitoringReport;

import java.time.LocalDateTime;

public record InsertMonitoringReportCommand(String deviceCode, float temperature, float humidity, LocalDateTime timestamp) {
}
