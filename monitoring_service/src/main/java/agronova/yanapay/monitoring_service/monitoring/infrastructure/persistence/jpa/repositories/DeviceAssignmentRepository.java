package agronova.yanapay.monitoring_service.monitoring.infrastructure.persistence.jpa.repositories;

import agronova.yanapay.monitoring_service.monitoring.domain.model.entities.DeviceAssignment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DeviceAssignmentRepository extends MongoRepository<DeviceAssignment, String> {
    Optional<DeviceAssignment> findByDeviceCode(String deviceCode);
}
