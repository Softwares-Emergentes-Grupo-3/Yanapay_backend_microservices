package agronova.yanapay.monitoring.infrastructure.persistence.jpa.repositories;

import agronova.yanapay.monitoring.domain.model.aggregates.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    Optional<Device> findByDeviceCode(String name);

    boolean existsByDeviceCode(String deviceCode);

    Set<Device> findAllByGreenhouse_Id(Long greenhouseId);
}
