package agronova.yanapay.iam.infrastructure.persistence.jpa.repositories;

import agronova.yanapay.iam.domain.models.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
