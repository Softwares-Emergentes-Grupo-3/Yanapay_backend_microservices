package agronova.yanapay.greenhouses.interfaces.getGreenhouseById;

import agronova.yanapay.monitoring.domain.model.aggregates.Device;

import java.time.LocalDate;
import java.util.List;

public record GetGreenhouseByIdResponse(
        Long id,
        String name,
        LocalDate plantingDate,
        List<DeviceDTO> devices
) {
}
