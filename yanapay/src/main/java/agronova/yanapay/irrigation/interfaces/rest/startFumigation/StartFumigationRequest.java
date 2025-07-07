package agronova.yanapay.irrigation.interfaces.rest.startFumigation;

import jakarta.validation.constraints.NotNull;

public record StartFumigationRequest(
        @NotNull(message = "Greenhouse ID cannot be null")
        Long greenhouseId) {
}
