package agronova.yanapay.greenhouses.domain.services.createGreenhouse;

import java.time.LocalDate;

public record CreateGreenhouseCommand(Long userId, String name, LocalDate plantingDate) {
}
