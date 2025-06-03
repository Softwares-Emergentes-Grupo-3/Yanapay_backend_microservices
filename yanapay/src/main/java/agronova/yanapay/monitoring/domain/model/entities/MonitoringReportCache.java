package agronova.yanapay.monitoring.domain.model.entities;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("monitoring_report_cache")
public class MonitoringReportCache {
    @Id
    private String id;

    private Long greenhouseId;
}
