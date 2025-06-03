package agronova.yanapay.greenhouses.domain.model.infrastructure.persistence.jpa.repositories;

import agronova.yanapay.greenhouses.domain.model.aggregates.Greenhouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GreenhouseRepository extends JpaRepository<Greenhouse, Long> {
}
