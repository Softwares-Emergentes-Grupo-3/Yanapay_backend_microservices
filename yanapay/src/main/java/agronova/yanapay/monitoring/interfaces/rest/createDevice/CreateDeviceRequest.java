package agronova.yanapay.monitoring.interfaces.rest.createDevice;

import jakarta.validation.constraints.NotBlank;

public record CreateDeviceRequest (
    @NotBlank(message = "Device code cannot be blank")
    String deviceCode
) {}
