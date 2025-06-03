package agronova.yanapay.irrigation.interfaces.rest.stopFumigation;

import jakarta.validation.constraints.NotNull;

public record StopFumigationRequest(
        @NotNull(message = "Greenhouse ID cannot be null")
        Long greenhouseId
) {
}
