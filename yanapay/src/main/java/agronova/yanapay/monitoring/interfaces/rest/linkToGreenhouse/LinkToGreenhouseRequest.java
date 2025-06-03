package agronova.yanapay.monitoring.interfaces.rest.linkToGreenhouse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LinkToGreenhouseRequest(
    @NotBlank(message = "Device code cannot be blank")
    String deviceCode,
    @NotNull(message = "Greenhouse ID cannot be blank")
    Long greenhouseId
) {
}
