package agronova.yanapay.irrigation.interfaces.rest.stopWatering;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StopWateringRequest(
        @NotNull(message = "Greenhouse ID cannot be null")
        Long greenhouseId) {
}
