package agronova.yanapay.monitoring_service.monitoring.infrastructure.persistence.jpa.repositories;

import agronova.yanapay.monitoring_service.monitoring.domain.model.entities.MonitoringReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MonitoringReportRepository extends MongoRepository<MonitoringReport, String> {
    Optional<MonitoringReport> findTopByGreenhouseIdOrderByTimestampDesc(long greenhouseId);
    List<MonitoringReport> findByGreenhouseIdAndTimestampAfterOrderByTimestampAsc(long greenhouseId, LocalDateTime timestamp);
}
