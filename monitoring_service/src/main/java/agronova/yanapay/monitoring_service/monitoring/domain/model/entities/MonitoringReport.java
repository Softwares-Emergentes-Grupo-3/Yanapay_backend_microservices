package agronova.yanapay.monitoring_service.monitoring.domain.model.entities;

import agronova.yanapay.monitoring_service.monitoring.domain.services.insertMonitoringReport.InsertMonitoringReportCommand;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@Document(collection = "monitoring_reports")
public class MonitoringReport {
    @Id
    private String id;
    private String deviceCode;
    private Long greenhouseId;
    private float temperature;
    private float humidity;
    private LocalDateTime timestamp;

    public static MonitoringReport fromInsertMonitoringReportCommand(InsertMonitoringReportCommand command) {
        MonitoringReport report = new MonitoringReport();

        report.setDeviceCode(command.deviceCode());
        report.setTemperature(command.temperature());
        report.setHumidity(command.humidity());
        report.setTimestamp(command.timestamp());

        return report;
    }
}
