package agronova.yanapay.greenhouses.interfaces.getDevicesByGreenhouseId;

import agronova.yanapay.monitoring.domain.model.aggregates.Device;

import java.util.List;
import java.util.Set;

public class GetDevicesByGreenhouseIdResponse {

    public final List<GetDevicesByGreenhouseDTO> data;

    public GetDevicesByGreenhouseIdResponse(Set<Device> devices) {
        this.data = devices.stream()
                .map(device -> new GetDevicesByGreenhouseDTO(
                        device.getId(),
                        device.getDeviceCode(),
                        device.isOnline()
                ))
                .toList();
    }
}

record GetDevicesByGreenhouseDTO(Long id, String deviceCode, boolean isOnline) {
}

