package agronova.yanapay.greenhouses.interfaces.createGreenhouse;

import jakarta.validation.constraints.NotBlank;

public record CreateGreenhouseRequest(
        @NotBlank(message = "Name cannot be blank")
        String name
) {}
