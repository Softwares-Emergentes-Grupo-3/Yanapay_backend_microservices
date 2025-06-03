package agronova.yanapay.monitoring.interfaces.rest.unlinkToGreenhouse;

import jakarta.validation.constraints.NotBlank;

public record UnlinkToGreenhouseRequest(
        @NotBlank(message = "Device code cannot be blank")
        String deviceCode
)
{ }
