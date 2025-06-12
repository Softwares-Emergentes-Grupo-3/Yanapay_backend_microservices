package agronova.yanapay.greenhouses.infrastructure.infrastructure.persistence.jpa.repositories;

import agronova.yanapay.greenhouses.domain.model.aggregates.Greenhouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GreenhouseRepository extends JpaRepository<Greenhouse, Long> {
    Optional<Greenhouse> findByName(String name);

    void deleteById(Long id);

    boolean existsGreenhouseById(Long id);

    boolean existsByName(String name);
}
