package agronova.yanapay.monitoring.infrastructure.persistence.jpa.repositories;

import agronova.yanapay.monitoring.domain.model.entities.DeviceAssignment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeviceAssignmentRepository extends MongoRepository<DeviceAssignment, String> {
    void deleteByDeviceCode(String deviceCode);
}
