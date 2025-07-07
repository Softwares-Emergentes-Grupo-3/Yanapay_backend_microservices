package agronova.yanapay.greenhouses.infrastructure.infrastructure.persistence.jpa.repositories;

import agronova.yanapay.greenhouses.domain.model.aggregates.Greenhouse;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GreenhouseRepository extends JpaRepository<Greenhouse, Long> {
    Optional<Greenhouse> findByName(String name);

    void deleteById(Long id);

    boolean existsGreenhouseById(Long id);

    boolean existsByName(String name);

    boolean existsByNameAndUser_Id(String name, Long id);

    List<Greenhouse> findByUser_Id(Long userId);
}
