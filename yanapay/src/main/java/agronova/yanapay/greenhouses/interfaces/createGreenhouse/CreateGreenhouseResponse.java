package agronova.yanapay.greenhouses.interfaces.createGreenhouse;

import java.time.LocalDate;

public record CreateGreenhouseResponse(Long id, String name, LocalDate plantingDate) {

}
