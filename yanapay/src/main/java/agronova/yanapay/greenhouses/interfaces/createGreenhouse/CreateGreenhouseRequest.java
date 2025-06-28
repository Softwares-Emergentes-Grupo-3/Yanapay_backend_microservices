package agronova.yanapay.greenhouses.interfaces.createGreenhouse;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateGreenhouseRequest(
        @NotNull(message = "User ID cannot be null")
        Long userId,

        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotNull(message = "Planting date cannot be blank")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate plantingDate
) {}
