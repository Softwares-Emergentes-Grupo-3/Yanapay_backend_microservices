package agronova.yanapay.irrigation.interfaces.rest.startWatering;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StartWateringRequest(
        @NotNull(message = "Greenhouse ID cannot be null")
        Long greenhouseId) {
}
