package agronova.yanapay.monitoring_service.monitoring.infrastructure.persistence.jpa.repositories;

import agronova.yanapay.monitoring_service.monitoring.domain.model.entities.MonitoringReportCache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoringReportCacheRepository extends CrudRepository<MonitoringReportCache, String> {
}
