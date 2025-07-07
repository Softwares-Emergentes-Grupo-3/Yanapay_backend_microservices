package agronova.yanapay.greenhouses.interfaces.updateGreenhouse;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateGreenhouseRequest(
        @NotNull(message = "id cannot be null")
        Long id,
        @NotBlank(message = "Name cannot be blank")
        String name
) {
}
