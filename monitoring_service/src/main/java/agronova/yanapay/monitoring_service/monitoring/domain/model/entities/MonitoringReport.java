package agronova.yanapay.monitoring_service.monitoring.domain.model.entities;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "monitoring_reports")
public class MonitoringReport {
    @Id
    private String id;
    private String deviceCode;
    private Long greenhouseId;
    private float temperature;
    private float humidity;
    private LocalDateTime timestamp = LocalDateTime.now();
}
