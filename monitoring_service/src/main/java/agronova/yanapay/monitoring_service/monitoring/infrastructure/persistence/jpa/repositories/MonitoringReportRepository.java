package agronova.yanapay.monitoring_service.monitoring.infrastructure.persistence.jpa.repositories;

import agronova.yanapay.monitoring_service.monitoring.domain.model.entities.MonitoringReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoringReportRepository extends MongoRepository<MonitoringReport, String> {
}
