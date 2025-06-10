package agronova.yanapay.greenhouses.interfaces.getGreenhouseById;

import agronova.yanapay.monitoring.domain.model.aggregates.Device;

import java.util.List;

public record GetGreenhouseByIdResponse(
        List<DeviceDTO> devices
) {
}
